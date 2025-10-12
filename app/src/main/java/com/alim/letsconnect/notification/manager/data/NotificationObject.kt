package com.alim.letsconnect.notification.manager.data

import com.google.gson.annotations.SerializedName

data class NotificationSuperObject(
    @SerializedName("Headers")
    val headers: Headers?,
    @SerializedName("Key")
    val key: String?,
    @SerializedName("Timestamp")
    val timestamp: Timestamp?,
    @SerializedName("Value")
    val extraData: Map<String?, String?>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("TargetNationalID")
    val targetNationalID: String?
) {
    fun toNotification(title: String? = null, body: String? = null) = Notification(
        reference = headers?.reference,
        objectId = headers?.objectID,
        targetNationalId = targetNationalID.orEmpty(),
        title = title,
        body = body,
        action = headers?.action,
        extraData = extraData.orEmpty(),
        event = headers?.event,
        entity = headers?.entity
    )
}

data class Headers(
    @SerializedName("Reference")
    val reference: String?,
    @SerializedName("Event")
    val event: String?,
    @SerializedName("Product")
    val product: String?,
    @SerializedName("Entity")
    val entity: String?,
    @SerializedName("Action")
    val action: String?,
    @SerializedName("Feature")
    val feature: String?,
    @SerializedName("ObjectId")
    val objectID: String?
)

data class Timestamp(
    @SerializedName("Type")
    val type: Long?,
    @SerializedName("UnixTimestampMs")
    val unixTimestampMS: Long?,
    @SerializedName("UtcDateTime")
    val utcDateTime: String?
)