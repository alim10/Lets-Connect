package org.alimapps.letsconnect.core.common.nativeLib

class NativeApiImpl : NativeApi {


    init {
        System.loadLibrary("native-api-urls")  // Load your .so library
    }

    external override fun getLoginUrl(): String

    external override fun getWinnersUrl(): String

    external override fun getNativeMessage(): String

}
