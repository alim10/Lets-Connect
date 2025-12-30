package org.alimapps.letsconnect.core.common.logging
import android.util.Log
import org.alimapps.letsconnect.core.BuildConfig

object AppLogs {

    private val isDebug = _root_ide_package_.org.alimapps.letsconnect.core.BuildConfig.DEBUG

    fun log(tag: String?, message: String) {
        if (isDebug) {
            Log.i(tag, message + "")
        }
    }

    fun handleException(tag: String?, e: Exception?) {
        if (isDebug) {
            if (e != null) {
                Log.d(tag, e.toString() + "")
            }
        }
    }

    fun handleThrowable(tag: String?, t: Throwable?) {
        if (isDebug) {
            if (t != null) {
                Log.d(tag, t.toString() + "")
            }
        }
    }
}