package org.alimapps.letsconnect.notification.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatestNotificationItem(
    val id: String?,
    val title: String,
    val description: String,
    val link: String,
): Parcelable
