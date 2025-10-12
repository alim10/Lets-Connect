package com.alim.letsconnect.notification

import com.alim.letsconnect.domain.ResponseResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsRepository @Inject constructor(
    private val remote: NotificationsRemote,
    private val cache: NotificationsCache,
) : INotificationsRepository {
    override fun isFcmTokenRegistered(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

//    override fun isFcmTokenRegistered(): Flow<Boolean> = cache.isFcmTokenRegistered()
    
    override suspend fun setFcmTokenRegistered(isRegistered: Boolean) =
        cache.setFcmTokenRegistered(isRegistered)
    


    override fun getFcmToken(): Flow<String> = remote.getFcmToken()
    override fun disableFcmToken(): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun registerFcmToken(token: String): Flow<ResponseResult<Unit>> {
        TODO("Not yet implemented")
    }

    override fun refreshPrivateNotifications(isPrivate: Boolean): Flow<ResponseResult<Unit>> {
        TODO("Not yet implemented")
    }


    override fun deleteNotification(
        nationalId: String,
        deleteNotificationId: String
    ) = remote.deleteNotification(nationalId, deleteNotificationId)

    override fun deletePrivateNotification(notificationId: String): Flow<Unit> {
        TODO("Not yet implemented")
    }
    //     cache.deleteNotificationById(deleteNotificationId)
    
    override suspend fun deleteCachePrivateNotification(notificationId: String) = cache.deletePrivateNotificationById(notificationId)
    
    override fun deleteAllNotifications(isPrivate: Boolean) = remote.deleteAllNotifications(isPrivate)
    override suspend fun deleteCacheAllNotifications(isPrivate: Boolean) = cache.deleteAllNotifications()
    
    override fun deleteAllPrivateNotifications(isPrivate: Boolean) = remote.deleteAllNotifications(isPrivate)
    override suspend fun deleteCacheAllPrivateNotifications(isPrivate: Boolean) = cache.deleteAllPrivateNotifications(isPrivate)
    
    override fun readNotification(nationalId: String, notificationCenterItemId: String) = remote.readNotification(nationalId, notificationCenterItemId)
    override suspend fun readCacheNotification(nationalId: String, notificationCenterItemId: String) = cache.readNotificationByIds(notificationCenterItemId)
    override fun readPrivateNotification(notificationId: String): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun readCacheAllPrivateNotifications() = cache.readAllPrivateNotifications()
    override suspend fun readCachePrivateNotification(notificationId: String) = cache.readPrivateNotification(notificationId)
    override fun readAllPrivateNotifications(): Flow<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun setLatestNotificationAsShown() = cache.setLatestNotificationAsShown()
    
    override suspend fun setAllNotificationsAsRead() = cache.setAllNotificationsAsRead()
}