package org.alimapps.letsconnect.notification.local.model

import android.R.attr.data
import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import org.alimapps.letsconnect.data.converters.LatestNotificationConverter
import org.alimapps.letsconnect.notification.domain.model.LatestNotification
import org.alimapps.letsconnect.notification.domain.model.LatestNotificationItem

@Keep
@Parcelize
@Entity(tableName = "tbl_latest_notifications", primaryKeys = ["id"])
@TypeConverters(LatestNotificationConverter::class)
data class CachedLatestNotifications(
    val id: String = "1",
    val unreadCount: Int?,
    val hasPopupNotification: Boolean,
    val data: LatestNotificationItem?

) : Parcelable {
    fun toDomain(): LatestNotification = LatestNotification(
        unreadCount = unreadCount,
        hasPopupNotification = hasPopupNotification,
        data = data
    )

    companion object {
        fun fromDomain(domain: LatestNotification): CachedLatestNotifications = with(domain) {
            CachedLatestNotifications(
                unreadCount = unreadCount,
                hasPopupNotification = hasPopupNotification,
                data = data
            )
        }
    }

}