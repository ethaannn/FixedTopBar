package com.ethan.ceiling

import android.app.Application
import com.ethan.ceiling.common.koin.modules.appKoinModules
import com.ethan.ceiling.common.koin.modules.mKoinManagerModules
import com.ethan.ceiling.manager.ILoggerManager
import com.ethan.ceiling.manager.IRuntimeManager
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application(), KoinComponent {
    private val mImplRuntimeManager: IRuntimeManager by inject()
    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
        val statusBarHeight = UltimateBarX.getStatusBarHeight()
        mImplRuntimeManager.statusBarHeight = statusBarHeight
        mLogger.debug("LOG:App:onCreate: mImplRuntimeManager={}", mImplRuntimeManager)
    }

    private fun initLogger() {
        get<ILoggerManager>().init()
    }
    private fun initKoin() {
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@App)
            modules(appKoinModules, mKoinManagerModules)
        }
    }
}