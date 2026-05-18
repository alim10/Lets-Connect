package org.alimapps.letsconnect.domain

import org.alimapps.letsconnect.core.common.networks.WrappedResponse
import org.alimapps.letsconnect.core.common.logging.AppLogs
import org.alimapps.letsconnect.core.common.utils.Const
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReviewsUseCase  @Inject constructor(private val repository: ReviewRepository) {

    suspend fun submitReview(request: RequestBody): Flow<ResponseResult<Any>> =
        flow {
            try {
                val response = repository.submitReview(request)
                if (response.isSuccessful && response.body() != null) {
                    val result = response.body()!!
                    if (result.status) {

                    } else {
                        emit(
                            ResponseResult.Message(
                                result.getLocalizedMessage()
                            )
                        )
                    }
                } else {
                    val type = object : TypeToken<WrappedResponse<Any>>() {}.type
                    val err: WrappedResponse<Any> =
                        Gson().fromJson(response.errorBody()!!.charStream(), type)
                    err.code = response.code()
                    emit(ResponseResult.Error(response.code()))
                }
            } catch (e: HttpException) {
                AppLogs.log(
                    "SharedBusinessUseCase",
                    e.localizedMessage ?: "An unexpected error occurred"
                )
                AppLogs.handleException("SharedBusinessUseCase", e)
                emit(ResponseResult.Error(Const.ERROR_HTTP_EXCEPTION))
            } catch (e: IOException) {
                emit(ResponseResult.Error(Const.ERROR_IO_EXCEPTION))
            }
        }

//    suspend fun reviews(
//        supplierId: Boolean,
//    ): Flow<PagingData<Any>> =
//        repository.reviews(supplierId).map { post ->
//            post.map { it }
//        }
//
}