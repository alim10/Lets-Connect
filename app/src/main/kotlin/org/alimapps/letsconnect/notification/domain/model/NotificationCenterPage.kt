package org.alimapps.letsconnect.notification.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Notifications(
    val id: String,
    val title: String,
    val description: String,
    val type: String?,
    val link: String,
    val read: Boolean
) : Parcelable