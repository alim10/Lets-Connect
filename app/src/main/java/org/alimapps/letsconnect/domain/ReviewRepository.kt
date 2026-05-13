package org.alimapps.letsconnect.domain

import org.alimapps.letsconnect.core.networks.WrappedResponse
import okhttp3.RequestBody
import retrofit2.Response

interface ReviewRepository {

    suspend fun submitReview(request: RequestBody): Response<WrappedResponse<Any>>

}