package org.alimapps.letsconnect.notification.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class NotificationCenterType(
    val id: String?,
    val name: String?
): Parcelable

@Keep
@Parcelize
data class NotificationCategory(
    val id: String?,
    val name: String?
): Parcelable