package org.alimapps.letsconnect.core.network.retrofit.exceptions

import android.util.Log
import com.google.gson.Gson
import com.lean.sehhaty.common.general.ErrorObject
import com.lean.sehhaty.network.retrofit.error.GeneralRemoteError
import com.lean.sehhaty.utility.utils.toGson
import com.lean.sehhaty.utility.utils.toObject
import org.alimapps.letsconnect.core.data.general.ErrorObject
import org.alimapps.letsconnect.core.network.retrofit.error.GeneralRemoteError
import retrofit2.HttpException
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStreamReader
import java.net.BindException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.PortUnreachableException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.net.UnknownServiceException
import java.util.concurrent.TimeoutException

object GeneralExceptionHandler {
    fun handleAllKindOfExceptions(
        error: Throwable,
        response: String?,
        code: Int?,
    ): ErrorObject {
        return when {
            response != null -> {
                return try {
                    handleException(error)
                } catch (e: Exception) {
                    val res = Gson().fromJson(response, GeneralRemoteError::class.java)
                    ErrorObject(
                        code = code ?: res.code ?: res.statusCode,
                        message = res.message ?: res.MessageBody,
                        additionalInfo = res.MessageTitle
                    )
                }
            }

            else -> handleException(error)
        }
    }

    private fun handleException(t: Throwable): ErrorObject {
        return when (t) {
            is HttpException -> {
                return try {
                    val res = createStringFromErrorBody(t).toObject(GeneralRemoteError::class.java)
                    Log.e("TAG", "handleException > Exception >> ${res.toGson()}")
                    ErrorObject(
                        code = res?.code ?: res?.statusCode ?: res?.ErrorCode?.toInt() ?: t.code(),
                        message = res?.message ?: res?.MessageBody,
                        additionalInfo = res?.MessageTitle
                    )
                } catch (e: Exception) {
                    Log.e("TAG", "handleException > Exception >> ${e.message}")
                    ErrorObject.default()
                }
            }
            is SocketTimeoutException -> ErrorObject.connectionError()
            is TimeoutException -> ErrorObject.connectionError()
            is UnknownHostException -> ErrorObject.connectionError()
            is ConnectException -> ErrorObject.connectionError()
            is NoRouteToHostException -> ErrorObject.default()
            is PortUnreachableException -> ErrorObject.default()
            is UnknownServiceException -> ErrorObject.default()
            is BindException -> ErrorObject.default()
            is IOException -> {
                // For retry request or file not found
                when (t) {
                    is FileNotFoundException -> ErrorObject.fileNotFoundError()
                    else -> ErrorObject.connectionError()
                }
            }

            else -> ErrorObject.default()
        }
    }

    private fun createStringFromErrorBody(response: HttpException): String {
        val reader: BufferedReader?
        val sb = java.lang.StringBuilder()
        try {
            reader = BufferedReader(InputStreamReader(response.response()?.errorBody()?.byteStream()))
            var line: String?
            try {
                while ((reader.readLine().also { line = it }) != null) {
                    sb.append(line?.trim())
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return sb.toString()
    }
}