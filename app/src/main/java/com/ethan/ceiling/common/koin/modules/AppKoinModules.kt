package com.ethan.ceiling.common.koin.modules

import com.google.common.eventbus.AsyncEventBus
import com.google.common.eventbus.EventBus
import org.koin.dsl.module
import java.util.concurrent.Executors

@Suppress(names = ["UnstableApiUsage"])
val appKoinModules = module {
    single<EventBus> { AsyncEventBus("FixedTopBar", Executors.newCachedThreadPool()) }
}