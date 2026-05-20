package com.lean.sehhaty.network.retrofit.responseHelpers

import androidx.core.os.bundleOf
import com.lean.sehhaty.analytics.Analytics
import com.lean.sehhaty.analytics.AnalyticsHelper
import com.lean.sehhaty.core.BuildConfig
import com.lean.sehhaty.network.retrofit.error.RemoteError
import com.lean.sehhaty.network.retrofit.error.RemoteErrorObject
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class NetworkResponseCall<S : Any, E : Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>,
    val analytics: Analytics
) : Call<NetworkResponse<S, E>> {


    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        // Response is successful but the body is null
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }
                    }
                    if (errorBody != null) {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.ApiError(errorBody, code))
                        )

                        if (BuildConfig.FLAVOR_build == "prod") {
                            val url = delegate.request().url.toString().takeLast(42)

                            analytics.logCustomEvent(
                                AnalyticsHelper.Events.SERVER_ERROR,
                                bundleOf(
                                    "code" to code,
                                    "message" to buildErrorMessage(errorBody),
                                    "url" to url
                                )
                            )
                        }

                    } else {
                        callback.onResponse(
                            this@NetworkResponseCall,
                            Response.success(NetworkResponse.UnknownError(null, code))
                        )
                    }
                }
            }

            override fun onFailure(call: Call<S>, throwable: Throwable) {
                val networkResponse = when (throwable) {
                    is IOException -> NetworkResponse.NetworkError(throwable)
                    else -> NetworkResponse.UnknownError(throwable)
                }
                callback.onResponse(this@NetworkResponseCall, Response.success(networkResponse))
            }
        })
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(
        delegate.clone(),
        errorConverter,
        analytics
    )

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun buildErrorMessage(errorBody: Any?): String? {
        return (errorBody as RemoteError).errors?.let<ArrayList<RemoteErrorObject>, String> {
            if (it.isNotEmpty())
                "Code: ${it[0].code}, Message: ${it[0].message}"
            else
                "UNDEFINED MESSAGE"
        }
    }
}