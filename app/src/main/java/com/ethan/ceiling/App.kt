package com.ethan.ceiling

import android.app.Application
import com.ethan.ceiling.common.koin.modules.appKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
    private fun initKoin() {
        startKoin {
            androidLogger(level = Level.INFO)
            androidContext(androidContext = this@App)
            modules(appKoinModules)
        }
    }
}