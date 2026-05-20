package org.alimapps.letsconnect.notification.domain.model

data class GetPrivateNotificationsResponse(
    val count: Int,
    val unreadCount: Int,
    val nextPageId: String,
    val notificationsList: List<PrivateNotificationItem>
)