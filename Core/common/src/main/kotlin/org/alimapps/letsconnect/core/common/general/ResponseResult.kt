package org.alimapps.letsconnect.core.common.general

import kotlinx.coroutines.flow.*

/**
 *
 * Currently we're using [Resource] into handling ResponseResults
 *
 * but it has an issue that we need to check on it's [StateData]
 * and dealing with nullable data or error object.
 *
 * instead this class implement Kotlin sealed class which made it easy to check the state without
 * dealing with nullable data.
 *
 * another note into Data layer we don't need to emit Loading state we just have two options
 * SUCCESS or ERROR this LOADING state is Presentation layer responsibility.
 */
sealed class ResponseResult<T> {
    data class Success<T>(val data: T) : ResponseResult<T>()
    data class Error<T>(val error: ErrorObject) : ResponseResult<T>()
    
    companion object {
        fun <T> success(data: T) = Success(data)
        fun <T> error(error: ErrorObject) = Error<T>(error)
    }
    
    fun getDataOrDefault(defaultValue: T) = when (this) {
        is Success -> data
        is Error -> defaultValue
    }
    
    suspend fun <R> onSuccess(action: suspend (T) -> R): ResponseResult<R> =
        when (this) {
            is Success -> success(action(data))
            is Error -> error(error)
        }
    
    suspend fun onError(action: suspend (ErrorObject) -> Unit): ResponseResult<T> =
        also { if (this is Error) action(error) }
    
    fun getOrNull(): T? = (this as? Success)?.data
}

fun <T, R> Flow<ResponseResult<T>>.mapSuccess(action: suspend (T) -> R) = map { state ->
    state.onSuccess { action(it) }
}

fun <T: Any> Flow<ResponseResult<T?>>.dataOrNull(): Flow<T?> = map { (it as? ResponseResult.Success)?.data }

fun <T: Any> Flow<ResponseResult<T?>>.filterDate(): Flow<T> = dataOrNull().filterNotNull()
