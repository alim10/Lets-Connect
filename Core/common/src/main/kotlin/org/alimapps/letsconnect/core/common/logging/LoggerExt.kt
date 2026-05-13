package org.alimapps.letsconnect.core.common.logging

/**
 * Call [Logger.d] on Any to print debug message
 */
fun Any.debug(message: Any?) = apply {
    Logger.d("$tag - ${message.toString()}")
}

/**
 * Call [Logger.e] on Any to print error message
 */
fun Any.error(message: Any?) = apply {
    Logger.e(message = "$tag - ${message.toString()}")
}

/**
 * Call [Logger.i] on Any to print info message
 */
fun Any.info(message: Any?) = apply {
    Logger.i("$tag - ${message.toString()}")
}

/**
 * Get tag of Java class
 */
private val Any.tag
    get() = javaClass.simpleName


