package com.ethan.ceiling

import android.app.Application
import android.content.Context
import com.ethan.ceiling.common.koin.modules.appKoinModules
import com.ethan.ceiling.common.koin.modules.mKoinManagerModules
import com.ethan.ceiling.manager.ILoggerManager
import com.ethan.ceiling.manager.IRuntimeManager
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.DefaultRefreshHeaderCreator
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
    companion object{
        init {
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, refreshLayout ->
                refreshLayout.setPrimaryColorsId(R.color.color_3BBECC, R.color.color_221613)
                ClassicsHeader(context)
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, refreshLayout ->

                ClassicsFooter(context).setDrawableSize(20F)
            }
        }
    }
    override fun onCreate() {
        super.onCreate()
        initKoin()
        initLogger()
        val statusBarHeight = UltimateBarX.getStatusBarHeight()
        mImplRuntimeManager.statusBarHeight = statusBarHeight
        mLogger.debug("LOG:App:onCreate: mImplRuntimeManager.statusBarHeight={}", mImplRuntimeManager.statusBarHeight)
    }
    private fun initKoin() {
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@App)
            modules(appKoinModules, mKoinManagerModules)
        }
    }
    private fun initLogger() {
        get<ILoggerManager>().init()
    }
}