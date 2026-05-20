package org.alimapps.letsconnect.core.network.util
import timber.log.Timber

class TimberLogging : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        return "(${element.fileName}:${element.lineNumber}) on ${element.methodName}"
    }
}