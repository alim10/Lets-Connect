package org.alimapps.letsconnect.data

import org.alimapps.letsconnect.core.networks.WrappedResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ReviewApiInterface {

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("api/user/get_posts_business_list")
    suspend fun reviews(@Body request: RequestBody): Response<WrappedResponse<Any>>

    @Headers("Content-Type:application/json;charset=UTF-8")
    @POST("api/user/get_posts_business_list")
    suspend fun submitReviews(@Body request: RequestBody): Response<WrappedResponse<Any>>

}