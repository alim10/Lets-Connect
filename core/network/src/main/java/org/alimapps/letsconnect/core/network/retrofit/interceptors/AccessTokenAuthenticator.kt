package org.alimapps.letsconnect.core.network.retrofit.interceptors

import android.util.Log
import com.lean.sehhaty.network.userToken.RefreshTokenRequest
import com.lean.sehhaty.network.userToken.RefreshTokenResponse
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.alimapps.letsconnect.core.analytics.event.EventPublisher
import org.alimapps.letsconnect.core.common.extension.isNotNull
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.network.clients.RetrofitUnauthorizedClient
import org.alimapps.letsconnect.core.network.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants.AUTH_HEADER
import org.alimapps.letsconnect.core.network.retrofit.ApiConstants.TOKEN_TYPE
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import timber.log.Timber
import java.util.Calendar
import java.util.Date
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccessTokenAuthenticator
@Inject
constructor(
    private val eventPublisher: EventPublisher,
    private val remoteConfig: IRemoteConfigRepository,
    unauthorizedClient: RetrofitUnauthorizedClient,
    private val appPrefs: SharedPrefsRepository,
) : Interceptor {
    private val authServiceApi = unauthorizedClient.getService(DummyAuthenticatorApi::class.java)
    private var oldAccessToken: String? = null
    private var newAccessToken: String? = null

    // AtomicBoolean in order to avoid race condition
    private var tokenRefreshInProgress: AtomicBoolean = AtomicBoolean(false)
    private var currentRefreshTimer: AtomicLong? = null

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val accessToken = appPrefs.accessToken

        val requestWithToken = originalRequest.newBuilder()
            .addHeader("__timer", getCurrentSeconds().toString())
            .addHeader("Authorization", "Bearer $accessToken")
            .build()

        val isExpired = isTokenExpired(appPrefs.tokenExpiredDate)

        oldAccessToken = appPrefs.accessToken

        return if (remoteConfig.getRefreshTokenExpirationFeatureKey() && isExpired && !tokenRefreshInProgress.get()) {
            newAccessToken = appPrefs.accessToken
            if (newAccessToken == oldAccessToken) {
                synchronized(this) {
                    tryRefreshToken(requestWithToken, 1)?.let { chain.proceed(it) }
                        ?: returnDefaultRequest(originalRequest)
                }
            } else chain.proceed(requestWithToken)

        } else {
            val response = try {
                chain.proceed(requestWithToken)
            } catch (_: Exception) {
                returnDefaultRequest(requestWithToken)
            }
            val isUnauthorized = response.code == 401
            val currentTimerForRequest =
                response.header("__timer", getCurrentSeconds().toString())?.toLongOrNull()
            if (isUnauthorized && !tokenRefreshInProgress.get()) {
                newAccessToken = appPrefs.accessToken
                if (newAccessToken == oldAccessToken
                    && (currentRefreshTimer == null || currentRefreshTimer?.get() == null || currentTimerForRequest == null
                            || (currentTimerForRequest - currentRefreshTimer?.get()!!) >= 60)
                ) {
                    synchronized(this) {
                        tryRefreshToken(requestWithToken, 1)
                        returnDefaultRequest(originalRequest)
                    }
                } else response
            } else response
        }
    }

    @Synchronized
    private fun tryRefreshToken(request: Request, numberOfTries: Int): Request? {
        currentRefreshTimer = AtomicLong(getCurrentSeconds())
        if (appPrefs.refreshToken.isNullOrEmpty()) {
            applyLogout()
            return null
        }
        val refreshTokenResponse =
            refreshToken(appPrefs.refreshToken, currentRefreshTimer?.get(), numberOfTries)
        return if (refreshTokenResponse.isSuccessful && refreshTokenResponse.body() != null) {
            Timber.e("====================== REFRESH TOKEN SUCCEED =====================")
            appPrefs.refreshToken = refreshTokenResponse.body()?.refresh_token
            appPrefs.accessToken = refreshTokenResponse.body()?.access_token
            newAccessToken = refreshTokenResponse.body()?.access_token
            tokenRefreshInProgress.set(false)
            resendRequest(request, refreshTokenResponse.body()?.access_token)
        } else if (numberOfTries < 1 && (refreshTokenResponse.code() != 401 || refreshTokenResponse.code() != 400)) {
            val numberRetries = numberOfTries + 1
            Timber.e("tryRefreshToken repeating >> $numberRetries")
            tryRefreshToken(request, numberRetries)
        } else {
            Timber.e("tryRefreshToken is logging out >>")
            applyLogout()
            null
        }
    }

    @Synchronized
    private fun resendRequest(request: Request, accessToken: String?): Request {
        Timber.e("AppDebug: resend request >> ${request.url}")
        return request.newBuilder()
            .header(AUTH_HEADER, TOKEN_TYPE + (accessToken ?: appPrefs.accessToken))
            .header("__timer", getCurrentSeconds().toString())
            .build()
    }

    private fun applyLogout(): Request? {
        Timber.e("====================== REFRESH TOKEN FAILED =====================")
//        eventPublisher.send(AppEvent.Logout)
        tokenRefreshInProgress.set(false)
        appPrefs.refreshToken = null
        return null
    }

    @Synchronized
    private fun refreshToken(
        refreshToken: String?,
        timer: Long?,
        numberOfTries: Int
    ): retrofit2.Response<RefreshTokenResponse> {
        return authServiceApi.refreshToken(
            timer = timer,
            count = numberOfTries,
            RefreshTokenRequest(refreshToken)
        ).execute()
    }

    private fun returnDefaultRequest(originalRequest: Request) = Response.Builder()
        .request(originalRequest)
        .code(202)
        .message("Token refresh in progress")
        .protocol(Protocol.HTTP_2)
        .body("".toResponseBody(null))
        .build()

    private fun getCurrentSeconds() = Calendar.getInstance().toInstant().epochSecond

    private fun isTokenExpired(tokenExpiredDate: Long?): Boolean {
        return false
//        if (tokenExpiredDate.isNotNull()) {
//            val calendar = Calendar.getInstance().apply {
//                time = Date(tokenExpiredDate ?: Date().time)
//                add(Calendar.SECOND, -((remoteConfig.getRefreshTokenFeatureKey()).toIntOrNull() ?: 60))
//            }
//            val adjustedExpirationDate = calendar.time
//            return Date() >= adjustedExpirationDate
//        } else {
//            applyLogout()
//            return true
//        }
    }
}

interface DummyAuthenticatorApi {
    @POST("REFRESH_TOKEN_URL")
    fun refreshToken(
        @Header("__timer") timer: Long? = 1,
        @Header("__counter") count: Int = 1,
        @Body refreshTokenRequest: RefreshTokenRequest
    ): Call<RefreshTokenResponse>
}