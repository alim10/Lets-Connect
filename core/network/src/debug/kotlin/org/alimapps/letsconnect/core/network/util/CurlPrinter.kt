package org.alimapps.letsconnect.core.network.util
import android.util.Log

object CurlPrinter {
    private var sTag: String = "CURL"
    private const val SINGLE_DIVIDER = "────────────────────────────────────────────"
    fun print(tag: String?, url: String, msg: String?): Pair<String, String> {
        // setting tag if not null
        if (tag != null) sTag = tag
        val logMsg = StringBuilder("\n")
        logMsg.append("\n")
        logMsg.append(msg)
        logMsg.append(" ")
        logMsg.append(" \n")
        log(logMsg.toString())
        return Pair(url, logMsg.toString())
    }
    
    private fun log(msg: String) {
        Log.d(sTag, msg)
    }
}