package org.alimapps.letsconnect.core.common.nativeLib

interface NativeApi {
    fun getNativeMessage(): String
    fun getLoginUrl(): String

    fun getWinnersUrl(): String
}
