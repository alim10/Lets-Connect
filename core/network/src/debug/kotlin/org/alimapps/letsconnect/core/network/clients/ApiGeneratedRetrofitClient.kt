package org.alimapps.letsconnect.core.network.clients
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.network.BuildConfig
import org.alimapps.letsconnect.core.network.interceptors.CertificatePinnerInterceptor
import org.alimapps.letsconnect.core.network.interceptors.CurlLoggerInterceptor
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants
import org.alimapps.letsconnect.core.network.retrofit.adapters.NetworkResponseAdapterFactory
import org.alimapps.letsconnect.core.network.retrofit.interceptors.ChuckInterceptorInstance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiGeneratedRetrofitClient @Inject constructor(
    private val chunkInterceptor: ChuckInterceptorInstance,
    private val curlLogger: CurlLoggerInterceptor,
    analytics: Analytics
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
            addInterceptor(chunkInterceptor.instance)
            addNetworkInterceptor(logger)
            readTimeout(ApiConstants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            connectTimeout(ApiConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            addInterceptor(curlLogger)
            certificatePinner(CertificatePinnerInterceptor.intercept())
            followRedirects(false)
            followSslRedirects(false)
        }
        val client = okHttpBuilder.build()

        val gson = GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()

        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(NetworkResponseAdapterFactory(analytics))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    fun <S> getService(service: Class<S>): S {
        return retrofit.create(service)
    }

    companion object {
        private const val BASE_URL = "https://leanapigenerator.herokuapp.com"
    }
}