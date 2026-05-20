package org.alimapps.letsconnect.notification.domain.repository

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.common.general.Location
import org.alimapps.letsconnect.core.common.general.ResponseResult
import org.alimapps.letsconnect.notification.domain.model.PrivateNotificationItem
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification

interface INotificationsRepository {
    
    fun isFcmTokenRegistered(): Flow<Boolean>
    
    suspend fun setFcmTokenRegistered(isRegistered: Boolean)
    
    fun getFcmToken(): Flow<String>
    
    fun disableFcmToken(): Flow<String>
    
    fun registerFcmToken(token: String): Flow<ResponseResult<Unit>>
    
    suspend fun insertPrivateNotificationsList(list: List<PrivateNotificationItem>)
    
    fun deleteNotification(
        nationalId: String,
        deleteNotificationId: String
    ): Flow<Unit>
    
    fun deletePrivateNotification(
        isPrivate: Boolean,
        notificationId: String
    ): Flow<Unit>

    fun getAllCacheNotificationCenter(
        isPrivate: Boolean,
    ): Flow<List<CachedPrivateNotification>>
    
    suspend fun deleteCachePrivateNotification(notificationId: String)
    
    fun deleteAllNotifications(isPrivate: Boolean): Flow<Unit>
    suspend fun deleteCacheAllNotifications(isPrivate: Boolean)
    fun deleteAllPrivateNotifications(isPrivate: Boolean): Flow<Unit>
    
    suspend fun deleteCacheAllPrivateNotifications(isPrivate: Boolean)
    
    fun readNotification(nationalId: String, notificationCenterItemId: String): Flow<Unit>
    suspend fun readCacheNotification(nationalId: String, notificationCenterItemId: String)
    
    fun readPrivateNotification(notificationId: String): Flow<Unit>
    suspend fun readCachePrivateNotification(notificationId: String)
    fun readAllPrivateNotifications(): Flow<Unit>
    suspend fun readCacheAllPrivateNotifications()
    
    suspend fun setLatestNotificationAsShown()
    suspend fun setAllNotificationsAsRead()
    
}