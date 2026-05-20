package org.alimapps.letsconnect.core.database.general
import android.content.Intent

interface OnGettingResultBack {
    fun onGettingResult(request: Int, data: Intent)
}