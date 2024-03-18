package com.ethan.psbc.commons.loggers

import java.util.logging.Level
import java.util.logging.Logger

class LoggerAppCenter : Logger("LoggerAppCenter", null) {
    private val logger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun log(level: Level, msg: String?) {
        //super.log(level, msg)
        if (level.intValue() >= Level.INFO.intValue()) {
            logger.info(msg)
        } else if (level.intValue() >= Level.WARNING.intValue()) {
            logger.warn(msg)
        } else if (level.intValue() >= Level.SEVERE.intValue()) {
            logger.error(msg)
        } else if (level.intValue() >= Level.FINE.intValue()) {
            logger.debug(msg)
        } else {
            logger.trace(msg)
        }
    }
}