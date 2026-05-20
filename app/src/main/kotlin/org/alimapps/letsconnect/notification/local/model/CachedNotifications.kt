package org.alimapps.letsconnect.notification.local.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize
import org.alimapps.letsconnect.data.converters.NotificationCenterTypeConverter
import org.alimapps.letsconnect.notification.domain.model.NotificationCenterItem
import org.alimapps.letsconnect.notification.domain.model.NotificationCenterType

@Keep
@Parcelize
@Entity(tableName = "tbl_notifications", primaryKeys = ["id"])
@TypeConverters(NotificationCenterTypeConverter::class)
data class CachedNotifications(
    val id: String,
    val title: String?,
    val description: String?,
    val type: NotificationCenterType?,
    val link: String?,
    val read: Boolean?
) : Parcelable {
    fun toDomain(): NotificationCenterItem = NotificationCenterItem(
        id = id,
        title = title,
        description = description,
        type = type,
        link = link,
        read = read
    )

    companion object {
        fun fromDomain(domain: NotificationCenterItem): CachedNotifications = with(domain) {
            CachedNotifications(
                id = id,
                title = title,
                description = description,
                type = type,
                link = link,
                read = read
            )
        }
    }

}