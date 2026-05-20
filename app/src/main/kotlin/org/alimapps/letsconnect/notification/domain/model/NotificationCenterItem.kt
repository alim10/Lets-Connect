package org.alimapps.letsconnect.notification.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotificationCenterItem(
    val id: String,
    val title: String?,
    val description: String?,
    val type: NotificationCenterType?,
    val link: String?,
    val read: Boolean?
) : Parcelable {
    @Parcelize
    data class Type(
        val id: String,
        val name: String?
    ) : Parcelable
}