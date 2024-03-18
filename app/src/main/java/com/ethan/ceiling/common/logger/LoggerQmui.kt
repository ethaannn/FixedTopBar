package com.ethan.psbc.commons.loggers

import com.qmuiteam.qmui.QMUILog

internal class LoggerQmui : QMUILog.QMUILogDelegate {

    private val mLogger: org.slf4j.Logger = org.slf4j.LoggerFactory.getLogger(this.javaClass)

    override fun e(tag: String?, msg: String?, vararg obj: Any?) {
        mLogger.error("{}:{} obj={}", tag, msg, obj.joinToString())
    }

    override fun w(tag: String?, msg: String?, vararg obj: Any?) {
        mLogger.warn("{}:{} obj={}", tag, msg, obj.joinToString())
    }

    override fun i(tag: String?, msg: String?, vararg obj: Any?) {
        mLogger.info("{}:{} obj={}", tag, msg, obj.joinToString())
    }

    override fun d(tag: String?, msg: String?, vararg obj: Any?) {
        mLogger.debug("{}:{} obj={}", tag, msg, obj.joinToString())
    }

    override fun printErrStackTrace(tag: String?, tr: Throwable?, format: String?, vararg obj: Any?) {
        mLogger.error("{}:{}  obj={}", tag, format, obj.joinToString(), tr)
    }
}