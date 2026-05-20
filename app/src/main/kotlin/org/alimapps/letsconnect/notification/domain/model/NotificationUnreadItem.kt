package org.alimapps.letsconnect.notification.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestNotification(
    val unreadCount: Int?,
    val hasPopupNotification: Boolean,
    val data: LatestNotificationItem?
) : Parcelable