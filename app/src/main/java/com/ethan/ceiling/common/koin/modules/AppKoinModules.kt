package com.ethan.ceiling.common.koin.modules

import com.ethan.ceiling.manager.ILoggerManager
import com.ethan.ceiling.manager.IRuntimeManager
import com.ethan.ceiling.manager.impls.ImplLoggerManager
import com.ethan.ceiling.manager.impls.ImplRuntimeManager
import com.google.common.eventbus.AsyncEventBus
import com.google.common.eventbus.EventBus
import org.koin.dsl.module
import java.util.concurrent.Executors

@Suppress(names = ["UnstableApiUsage"])
val appKoinModules = module {
    single<EventBus> { AsyncEventBus("FixedTopBar", Executors.newCachedThreadPool()) }

}

val mKoinManagerModules= module {
    single<IRuntimeManager> { ImplRuntimeManager() }
    single <ILoggerManager>{ ImplLoggerManager() }
}