package org.alimapps.letsconnect.core.network.retrofit.interceptors

import android.os.Build
import okhttp3.Interceptor
import okhttp3.Response
import org.alimapps.letsconnect.core.common.nativeLib.Secrets

const val DEVICE_TYPE = "ANDROID"

class AppHeader : Interceptor {
    private val userAgent = "User-Agent"
    private val appVersion = "" /*BuildConfig.VERSION_NAME*/
    private val os = "Android"
    private val osAPI = Build.VERSION.SDK_INT.toString()
    private val apiKey = "apikey"
    private val authType = "X-authorizationType"
    private val authTypeValue = "JWT"
    private val deviceType = "X-Device-Type"
    private val versionName = "X-Version-Name"
    private val xAppVersion = "X-App-Version"
    private val appVersionName = "App-Version"
    private val xApplication = "X-Application"
    private val xApplicationClient = "X-Application-Client"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .run {
//                addHeader(userAgent, "${BuildConfig.APP_NAME}/$appVersion ($os $osAPI)")
                addHeader(userAgent, "letsconnect/$appVersion ($os $osAPI)")
                addHeader(deviceType, DEVICE_TYPE)
                addHeader(versionName, appVersion)
                addHeader(authType, authTypeValue)
//                addHeader(xAppVersion, BuildConfig.VERSION_NAME)
                addHeader(xAppVersion, "XAppVersion")
                addHeader(appVersionName, "101")
//                addHeader(appVersionName, BuildConfig.VERSION_NAME)
                val url = chain.request().url.toString()
                addHeader(apiKey, Secrets.apiKey())

                if (url.contains("sehhaty/chat/files/system")) {
                    addHeader(xApplication, Secrets.xApplicationHeader())
                    addHeader(xApplicationClient, Secrets.xApplicationClientHeader())
                }

                build()
            }
        return try {
            chain.proceed(request)
        } catch (e: Exception) { chain.proceed(chain.request()) }
    }

    companion object {
        const val AGE = "X-Age"
        const val CREDENTIAL_NATIONAL_ID = "X-Credential-Nid"
        const val X_PASS_JWT = "X-Pass-JWT"
        const val SEHHATY = "Sehhaty"
        const val USER_INFORMATION = "User-Information"
    }
}