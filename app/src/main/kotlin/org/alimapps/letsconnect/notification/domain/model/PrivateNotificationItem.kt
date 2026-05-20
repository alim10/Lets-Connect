package org.alimapps.letsconnect.notification.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.alimapps.letsconnect.core.common.notification.Notification
import org.alimapps.letsconnect.core.common.notification.NotificationMainExtra

@Parcelize
data class PrivateNotificationItem(
    val id: String,
    val reference: String,
    val targetNationalId: String,
    val targetFullName: String,
    val title: String,
    val body: String,
    val isPrivate: Boolean,
    val isRead: Boolean,
    val createdAt: String,
    val extraData: String?,
    val extraDataModel: NotificationMainExtra?,
    val objectId: String?,
    val event: String?,
    val action: String?,
    val entity: String?,
) : Parcelable {
    fun toNotification() = Notification(
        reference = reference,
        objectId = objectId,
        targetNationalId = targetNationalId,
        title = title,
        body = body,
        extraData = /*extraData?.let { it } ?:*/ mapOf(),
        extraDataModel = extraDataModel,
        event = event,
        entity = entity,
        action = action,
    )
}
