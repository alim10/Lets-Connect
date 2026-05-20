package org.alimapps.letsconnect.notification.local.source

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.notification.local.model.CachedLatestNotifications
import org.alimapps.letsconnect.notification.local.model.CachedNotifications
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification
import javax.inject.Singleton

@Singleton
interface NotificationsCache {
    fun isFcmTokenRegistered(): Flow<Boolean>
    suspend fun setFcmTokenRegistered(isRegistered: Boolean)
    fun getNotificationsList(): Flow<List<CachedNotifications>?>
    fun getPrivateNotificationsList(isPrivate: Boolean): Flow<List<CachedPrivateNotification>>
    suspend fun insertNotificationList(vararg items: CachedNotifications)
    suspend fun insertPrivateNotificationList(items: List<CachedPrivateNotification>)
    suspend fun deleteNotificationById(id: String)
    suspend fun deletePrivateNotificationById(id: String)
    suspend fun getNotificationById(id: String): CachedNotifications?
    suspend fun deleteAllNotifications()
    suspend fun deleteAllPrivateNotifications(isPrivate: Boolean)
    suspend fun readNotificationByIds(id: String)
    suspend fun readPrivateNotification(id: String)
    suspend fun readAllPrivateNotifications()
    fun latestNotification(): Flow<CachedLatestNotifications?>
    suspend fun insertLatestNotification(item: CachedLatestNotifications)
    suspend fun setLatestNotificationAsShown()
    suspend fun setAllNotificationsAsRead()
    suspend fun clearLatestNotification()
}