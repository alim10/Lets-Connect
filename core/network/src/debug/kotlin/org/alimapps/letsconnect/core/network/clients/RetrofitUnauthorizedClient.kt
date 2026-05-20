package org.alimapps.letsconnect.core.network.clients

import android.content.Context
import com.google.gson.GsonBuilder
import com.lean.sehhaty.network.retrofit.interceptors.AppHeader
import com.lean.sehhaty.network.retrofit.interceptors.ChuckInterceptorInstance
import com.lean.sehhaty.network.retrofit.interceptors.LanguageInterceptor
import com.lean.sehhaty.network.retrofit.interceptors.MobileModeInterceptor
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.data.nativeLib.Secrets
import org.alimapps.letsconnect.core.data.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.network.BuildConfig
import org.alimapps.letsconnect.core.network.interceptors.CertificatePinnerInterceptor
import org.alimapps.letsconnect.core.network.interceptors.CurlLoggerInterceptor
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants
import org.alimapps.letsconnect.core.network.retrofit.adapters.NetworkResponseAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitUnauthorizedClient @Inject constructor(
    @ApplicationContext context: Context,
    analytics: Analytics,
    private val appPrefs: SharedPrefsRepository,
    private val chunkInterceptor: ChuckInterceptorInstance,
    private val curlLogger: CurlLoggerInterceptor,
) {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.apply {
            addInterceptor(AppHeader())
            addInterceptor(LanguageInterceptor(appPrefs))
            addInterceptor(MobileModeInterceptor(appPrefs, context = context))
            addInterceptor(chunkInterceptor.instance)
            addNetworkInterceptor(logger)
            addInterceptor(curlLogger)
            readTimeout(ApiConstants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            connectTimeout(ApiConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            certificatePinner(CertificatePinnerInterceptor.intercept())
            followRedirects(false)
            followSslRedirects(false)
        }
        val client = okHttpBuilder.build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(Secrets.baseUrl())
            .addCallAdapterFactory(NetworkResponseAdapterFactory(analytics))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    fun <S> getService(service: Class<S>): S {
        return retrofit.create(service)
    }
}
