package com.alim.letsconnect.domain

import com.alim.letsconnect.core.networks.WrappedResponse
import okhttp3.RequestBody
import retrofit2.Response

interface ReviewRepository {

    suspend fun submitReview(request: RequestBody): Response<WrappedResponse<Any>>

}