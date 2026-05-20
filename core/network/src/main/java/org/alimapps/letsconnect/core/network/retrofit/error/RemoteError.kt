package org.alimapps.letsconnect.core.network.retrofit.error

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RemoteError(
    @SerializedName("errors")
    val errors: ArrayList<RemoteErrorObject>?
)

data class RemoteErrorObject(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("additional_info")
    val additional_info: Any
)

data class RemoteEhalaError(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("msg_code")
    val messageCode: String?
)

data class RemoteTelehealthError(
    @SerializedName("ErrorCode")
    val code: String?,
    @SerializedName("Message")
    val message: String?
)

data class RemoteMawidError(
    @SerializedName("subErrors")
    val subError: List<RemoteVitalSignsError>?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("code")
    val code: Int?,
)

data class RemoteVitalSignsError(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?
)

data class RemoteTeamCareError(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("message")
    val message: String?
)

data class RemoteIndividualsError(
    @SerializedName("Message")
    val message: String?,
    @SerializedName("StatusCode")
    val statusCode: Int?,
    @SerializedName("MessageTitle")
    val MessageTitle: String?,
    @SerializedName("MessageBody")
    val MessageBody: String?,
    @SerializedName("ErrorCode", alternate = arrayOf("errorCode"))
    val errorCode: Int?)

data class RemoteCompanionError(
    @SerializedName("Message")
    val message: String?,
    @SerializedName("StatusCode")
    val statusCode: Int?)

@Keep
open class GeneralRemoteError(
    @SerializedName("MessageTitle", alternate = arrayOf("messageTitle"))
    val MessageTitle: String? = null,
    /**
     * @return ONLY code error
     * */
    @SerializedName("StatusCode", alternate = arrayOf("statusCode"))
    val statusCode: Int? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("ErrorCode", alternate = arrayOf("errorCode"))
    val ErrorCode: String? = null,
    /**
     * @return ONLY message
     * */
    @SerializedName("message", alternate = arrayOf("Message"))
    val message: String? = null,
    @SerializedName("MessageBody", alternate = arrayOf("messageBody"))
    val MessageBody: String? = null,
    /**
     * @return ONLY errors list
     * */
    @SerializedName("subErrors")
    val subError: List<RemoteErrorObject>? = null,
    @SerializedName("errors")
    val errors: List<RemoteErrorObject>? = null
): Serializable