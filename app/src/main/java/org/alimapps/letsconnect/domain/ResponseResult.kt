package org.alimapps.letsconnect.domain

sealed class ResponseResult<T>(val data: T? = null, val message: String? = null, val code: Int? = null) {
    class Success<T>(data: T) : ResponseResult<T>(data)
    class Error<T>(code: Int, data: T? = null) : ResponseResult<T>(data,  null, code)
    class Message<T>(message: String, data: T? = null) : ResponseResult<T>(data, message)
}


