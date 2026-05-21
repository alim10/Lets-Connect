package org.alimapps.letsconnect.core.network.interceptors

import android.R.style.Theme
import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MobileModeInterceptor @Inject constructor(val appPrefs: SharedPrefsRepository, val context: Context) : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader(
                ApiConstants.MOBILE_MODE,
                "SYSTEM"
//                ThemeUtils.getMobileDarkModeHeader(context, appPrefs.themePreference?.toTheme() ?: Theme.SYSTEM).toString()
            )
            .build()

        return try {
            chain.proceed(request)
        } catch (e: Exception) { chain.proceed(request) }
    }
}