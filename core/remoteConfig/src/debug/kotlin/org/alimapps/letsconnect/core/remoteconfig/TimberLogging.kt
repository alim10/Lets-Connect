package org.alimapps.letsconnect.core.remoteconfig

import timber.log.Timber

/**
 * In debug this method will provide the fileName and line as well method name.
 */
class TimberLogging : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}