package com.alim.letsconnect.logging

import timber.log.Timber

/**
 * Created by Ahmed Ibrahim on 07,August,2021
 *
 * In debug this method will provide the fileName and line as well method name.
 */
class TimberLogging : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}