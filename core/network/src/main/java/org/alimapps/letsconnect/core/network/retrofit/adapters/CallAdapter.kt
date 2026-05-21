package org.alimapps.letsconnect.core.network.retrofit.adapters

import okhttp3.ResponseBody
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.network.retrofit.responseHelpers.NetworkResponse
import org.alimapps.letsconnect.core.network.retrofit.responseHelpers.NetworkResponseCall
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type


class NetworkResponseAdapter<S : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>,
    val analytics: Analytics
) : CallAdapter<S, Call<NetworkResponse<S, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkResponseCall(
            call,
            errorBodyConverter,
            analytics
        )
    }
}