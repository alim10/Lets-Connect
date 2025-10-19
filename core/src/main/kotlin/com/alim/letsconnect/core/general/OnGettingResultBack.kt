package com.alim.letsconnect.core.general
import android.content.Intent

interface OnGettingResultBack {
    fun onGettingResult(request: Int, data: Intent)
}