package com.alim.letsconnect.core.general

import androidx.annotation.Keep

@Keep
data class ErrorObject(
    val code: Int? = -1,
    val message: String? = "",
    val additionalInfo: String? = null,
    val exception: Exception? = null
) {
    override fun toString() =
        "ErrorObject = code: $code, message: $message, additionalInfo: $additionalInfo, exception: ${exception?.message}"
    
    companion object {
        fun default() = ErrorObject(ErrorCodes.UNKNOWN_ERROR, null)
        fun emptyData() = ErrorObject(ErrorCodes.EMPTY_DATA_ERROR, null)
        fun connectionError() = ErrorObject(ErrorCodes.NETWORK_ERROR, null)
        fun fileNotFoundError() = ErrorObject(ErrorCodes.NETWORK_ERROR, null)
    }
}