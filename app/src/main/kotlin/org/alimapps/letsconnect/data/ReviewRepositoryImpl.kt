package org.alimapps.letsconnect.data

import org.alimapps.letsconnect.core.common.networks.WrappedResponse
import org.alimapps.letsconnect.domain.ReviewRepository
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ReviewRepositoryImpl @Inject constructor(private val apiInterface: ReviewApiInterface) :
    ReviewRepository {

    override suspend fun submitReview(request: RequestBody): Response<WrappedResponse<Any>> {
        return apiInterface.reviews(request)
    }

}