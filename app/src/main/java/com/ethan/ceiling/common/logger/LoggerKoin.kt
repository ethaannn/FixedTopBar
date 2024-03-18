package com.ethan.psbc.commons.loggers

import org.koin.core.logger.KOIN_TAG
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import org.slf4j.LoggerFactory

internal class LoggerKoin(level: Level = Level.NONE) : Logger(level) {

    private val mLogger: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)

    override fun display(level: Level, msg: MESSAGE) {
        if (this.level <= level) {
            logOnLevel(msg, level)
        }
    }

    private fun logOnLevel(msg: MESSAGE, level: Level) {
        when (level) {
            Level.DEBUG -> mLogger.debug("{} {}", KOIN_TAG, msg)
            Level.INFO  -> mLogger.info("{} {}", KOIN_TAG, msg)
            Level.ERROR -> mLogger.error("{} {}", KOIN_TAG, msg)
            else        -> mLogger.debug("{} {}", KOIN_TAG, msg)
        }
    }

}