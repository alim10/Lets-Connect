package org.alimapps.letsconnect.notification.domain.model

import java.io.Serializable

class UiNotificationState(
    val generalUnread: Int = 0,
    val hasUnread: Boolean = false,
    val privateUnread: Int = 0
): Serializable