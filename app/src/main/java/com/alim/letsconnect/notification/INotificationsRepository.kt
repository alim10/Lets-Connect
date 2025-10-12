package com.alim.letsconnect.notification

import com.alim.letsconnect.domain.ResponseResult
import kotlinx.coroutines.flow.Flow

interface INotificationsRepository {
    
    fun isFcmTokenRegistered(): Flow<Boolean>
    
    suspend fun setFcmTokenRegistered(isRegistered: Boolean)

    
    fun getFcmToken(): Flow<String>
    
    fun disableFcmToken(): Flow<String>
    
    fun registerFcmToken(token: String): Flow<ResponseResult<Unit>>
    
    fun refreshPrivateNotifications(isPrivate: Boolean): Flow<ResponseResult<Unit>>
    
    fun deleteNotification(
        nationalId: String,
        deleteNotificationId: String
    ): Flow<Unit>
    
    fun deletePrivateNotification(
        notificationId: String
    ): Flow<Unit>
    
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