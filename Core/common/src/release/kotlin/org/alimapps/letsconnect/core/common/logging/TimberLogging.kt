package org.alimapps.letsconnect.core.common.logging

import timber.log.Timber

/**
 * Created by Ahmed Ibrahim on 07,August,2021
 *
 * In release we could log crashlytics.
 */
class TimberLogging : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}

    private fun logWarning(priority: Int, tag: String?, message: String) {
        //Crashlytics.log(priority, tag, message)
    }

    private fun logError(t: Throwable?, priority: Int, tag: String?, message: String) {
        //Crashlytics.log(priority, tag, message)

        t?.let {
            //Crashlytics.logException(it)
        }
    }
}