package org.alimapps.letsconnect.notification

import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface NotificationsCache {
    fun isFcmTokenRegistered(): Flow<Boolean>
    suspend fun setFcmTokenRegistered(isRegistered: Boolean)
    suspend fun deleteNotificationById(id: String)
    suspend fun deletePrivateNotificationById(id: String)
    suspend fun deleteAllNotifications()
    suspend fun deleteAllPrivateNotifications(isPrivate: Boolean)
    suspend fun readNotificationByIds(id: String)
    suspend fun readPrivateNotification(id: String)
    suspend fun readAllPrivateNotifications()
    suspend fun setLatestNotificationAsShown()
    suspend fun setAllNotificationsAsRead()
    suspend fun clearLatestNotification()
}
