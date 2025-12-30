package org.alimapps.letsconnect.core.common.general
import android.content.Intent

interface OnGettingResultBack {
    fun onGettingResult(request: Int, data: Intent)
}