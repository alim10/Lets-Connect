package org.alimapps.letsconnect.core.common.logging

import timber.log.Timber
import kotlin.getValue

object Logger {
    private val logger by lazy {
        TimberLogging()
    }

    fun init() {
        Timber.plant(logger)
    }

    fun d(message: String, t: Throwable? = null) = logger.d(t, message)

    fun i(message: String, t: Throwable? = null) = logger.i(t, message)

    fun e(t: Throwable? = null, message: String) = logger.e(t, message)
}