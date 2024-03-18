package com.ethan.ceiling.manager

import android.net.Uri

internal interface ILoggerManager {

    fun init()

    suspend fun zip(): Uri
}
