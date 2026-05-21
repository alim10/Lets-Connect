package org.alimapps.letsconnect.core.network.retrofit.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants
import java.io.IOException
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageInterceptor @Inject constructor(val appPrefs: SharedPrefsRepository) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader(ApiConstants.LANGUAGE, appPrefs.locale.uppercase(Locale.ENGLISH))
            .build()

        return try {
            chain.proceed(request)
        } catch (e: Exception) { chain.proceed(request) }
    }
}