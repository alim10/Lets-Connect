package com.alim.letsconnect.core.logging
import android.util.Log
import com.alim.letsconnect.core.BuildConfig

object AppLogs {

    private val isDebug = BuildConfig.DEBUG

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