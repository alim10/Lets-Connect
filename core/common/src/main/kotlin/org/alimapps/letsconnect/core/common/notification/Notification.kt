package org.alimapps.letsconnect.core.common.notification
import java.io.Serializable

class Notification(
    val reference: String?,
    val objectId: String?,
    val targetNationalId: String?,
    val title: String?,
    val body: String?,
    val action: String?,
    val extraData: Map<String?, String?>,
    val extraDataModel: NotificationMainExtra? = null,
    val event: String?,
    val entity: String?
): Serializable