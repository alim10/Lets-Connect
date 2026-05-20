package org.alimapps.letsconnect.core.common.nativeLib

object ApiUrls {
    init {
        System.loadLibrary("native-api-urls")
    }
    external fun getBaseUrl(): String
    external fun getBannerUrl(): String
    external fun getLoginUrl(): String
    external fun getLoginOtpUrl(): String
    external fun getSeriesUrl(): String
    external fun getWinnersUrl(): String
    external fun getRoundsUrl(): String
    external fun getAllMatchUrl(): String
}

object Urls {
    val abd = ApiUrls.getBannerUrl()
    val abd2 = ApiUrls.getBannerUrl()
    val abd3 = ApiUrls.getLoginUrl()
}