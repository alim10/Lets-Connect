package org.alimapps.letsconnect.core.network.retrofit.adapters

import com.lean.sehhaty.analytics.Analytics
import com.lean.sehhaty.network.retrofit.responseHelpers.NetworkResponse
import com.lean.sehhaty.network.retrofit.responseHelpers.NetworkResponseCall
import okhttp3.ResponseBody
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