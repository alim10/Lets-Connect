package org.alimapps.letsconnect.core.network.di
import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.common.nativeLib.Secrets
import org.alimapps.letsconnect.core.network.clients.RetrofitClient
import org.alimapps.letsconnect.core.network.interceptors.CertificatePinnerInterceptor
import org.alimapps.letsconnect.core.network.interceptors.CurlLoggerInterceptor
import org.alimapps.letsconnect.core.network.interceptors.MobileModeInterceptor
import org.alimapps.letsconnect.core.network.interceptors.RewriteResponseCacheControlInterceptor
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants
import org.alimapps.letsconnect.core.network.retrofit.adapters.NetworkResponseAdapterFactory
import org.alimapps.letsconnect.core.network.retrofit.interceptors.AccessTokenAuthenticator
import org.alimapps.letsconnect.core.network.retrofit.interceptors.AppHeader
import org.alimapps.letsconnect.core.network.retrofit.interceptors.ChuckInterceptorInstance
import org.alimapps.letsconnect.core.network.retrofit.interceptors.LanguageInterceptor
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreNetworkModule {
    @Singleton
    @Provides
    fun provideOkhttpRequest(
        @ApplicationContext context: Context,
        appPrefs: SharedPrefsRepository,
        chunkInterceptor: ChuckInterceptorInstance,
        curlLogger: CurlLoggerInterceptor,
        accessTokenAuthenticator: AccessTokenAuthenticator
    ) = OkHttpClient.Builder().apply {
        readTimeout(ApiConstants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        connectTimeout(ApiConstants.CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        addInterceptor(AppHeader())
        addInterceptor(LanguageInterceptor(appPrefs))
        addInterceptor(MobileModeInterceptor(appPrefs, context))
        addInterceptor(curlLogger)
        addInterceptor(chunkInterceptor.instance)
        addNetworkInterceptor(RewriteResponseCacheControlInterceptor())
        certificatePinner(CertificatePinnerInterceptor.intercept())
        followRedirects(false)
        followSslRedirects(false)
        addInterceptor(accessTokenAuthenticator)
    }.build()

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        analytics: Analytics
    ) = Retrofit.Builder()
        .baseUrl(Secrets.baseUrl())
        .addCallAdapterFactory(NetworkResponseAdapterFactory(analytics))
        .addConverterFactory(GsonConverterFactory.create(
            GsonBuilder()
            .setLenient()
            .serializeNulls()
            .create()
        ))
        .client(client)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(retrofit: Retrofit) = RetrofitClient(retrofit)
}

/**
 *
 * Use as
 * OkHttpClient.Builder().cache(cache)
 *
 * @receiver Context needed for obtaining the cachedir
 * @param cacheDirName String initial is "http-cache"
 * @param cacheSize Int initial is 10 * 1024 * 1024 // 10 MB
 * @return Cache
 */
fun Context.retrofitCache(
    cacheDirName: String = "http-cache",
    cacheSize: Int = 10 * 1024 * 1024
): Cache {
    val httpCacheDirectory = File(cacheDir, cacheDirName)
    return Cache(httpCacheDirectory, cacheSize.toLong())
}