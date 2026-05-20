package org.alimapps.letsconnect.notification.local.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.alimapps.letsconnect.core.common.notification.NotificationMainExtra
import java.io.Serializable

@Keep
@Entity(tableName = "notifications")
data class CachedPrivateNotification(
    @PrimaryKey val id: String,
    val reference: String,
    val targetNationalId: String,
    val targetDependencyRelation: Int,
    val targetFullName: String,
    val title: String,
    val body: String,
    val isPrivate: Boolean,
    val isRead: Boolean,
    val createdAt: String,
    val extraData: String?,
    val extraDataModel: NotificationMainExtra?,
    val objectId: String?,
    val event: String?,
    val action: String?,
    val entity: String?,
): Serializable