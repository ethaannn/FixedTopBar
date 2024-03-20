package com.ethan.ceiling.manager.impls

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import com.ethan.ceiling.manager.ILoggerManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.compress.archivers.zip.Zip64Mode
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream
import org.apache.commons.io.FileUtils
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileFilter
import java.util.Locale

internal class ImplLoggerManager : ILoggerManager, KoinComponent {
    private val mLogger: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)
    private val mContext: Context by inject()
    private val mOutputDir: File = File(mContext.externalCacheDir, "logs")

    override fun init() {
        val loggerContext: LoggerContext = LoggerFactory.getILoggerFactory() as LoggerContext
        loggerContext.reset()
        loggerContext.putProperty("CACHE_DIR", mContext.externalCacheDir!!.path)

        val logbackConfigurationFile = File(mContext.getExternalFilesDir("configs"), "logback.xml")
        mContext.assets.open("configs/logback.xml").use {
            logbackConfigurationFile.writeText(it.reader(charset = Charsets.UTF_8).readText())
        }

        val joranConfigurator = JoranConfigurator()
        joranConfigurator.context = loggerContext
        joranConfigurator.doConfigure(logbackConfigurationFile)
    }

    override suspend fun zip(): Uri = withContext(context = Dispatchers.IO) {
        val sharedDir = File(mContext.externalCacheDir, "shared")
        val fileName: String = String.format(Locale.ENGLISH, "logs_%s.zip", System.currentTimeMillis())
        val outputFile = File(sharedDir, fileName)
        if (outputFile.exists()) {
            outputFile.delete()
        }
        FileUtils.forceMkdir(outputFile.parentFile)

        sharedDir.listFiles(FileFilter { it.extension == "zip" && it.name.startsWith(prefix = "logs_") })
            ?.forEach { it.delete() }

        val zipArchiveOutputStream = ZipArchiveOutputStream(outputFile)
        zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded)
        zipArchiveOutputStream.encoding = "UTF-8"

        mOutputDir
            .listFiles(FileFilter { it.extension == "log" })
            ?.forEach { logFile ->
                val zipArchiveEntry = ZipArchiveEntry(logFile, logFile.name)
                zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry)
                zipArchiveOutputStream.write(logFile.readBytes())
                zipArchiveOutputStream.closeArchiveEntry()
            }
        zipArchiveOutputStream.finish()
        zipArchiveOutputStream.close()

        val packageName: String = mContext.packageName
        return@withContext FileProvider.getUriForFile(mContext, "$packageName.providers.file", outputFile)
    }
}