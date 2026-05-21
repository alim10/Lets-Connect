package org.alimapps.letsconnect.notification.local.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.data.AppDatabase
import org.alimapps.letsconnect.notification.local.model.CachedLatestNotifications
import org.alimapps.letsconnect.notification.local.model.CachedNotifications
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification
import javax.inject.Inject

class RoomNotificationsCache @Inject constructor(
    appDatabase: AppDatabase,
    private val appPrefs: SharedPrefsRepository
) : NotificationsCache {
    
    private val notificationsDao = appDatabase.notificationsDao()
    
    private val privateNotificationsDao = appDatabase.privateNotificationsDao()
    
    private val latestNotificationsDao = appDatabase.latestNotificationDao()
    
    override fun isFcmTokenRegistered(): Flow<Boolean> = flow {
        emit(appPrefs.isFcmTokenRegistered)
    }
    
    override suspend fun setFcmTokenRegistered(isRegistered: Boolean) {
        appPrefs.isFcmTokenRegistered = isRegistered
    }
    
    override fun getNotificationsList(): Flow<List<CachedNotifications>?> =
        notificationsDao.getNotificationsList()
    
    override fun getPrivateNotificationsList(isPrivate: Boolean): Flow<List<CachedPrivateNotification>> =
        privateNotificationsDao.getNotificationsList(isPrivate)
    
    override suspend fun insertNotificationList(vararg items: CachedNotifications) =
        notificationsDao.insert(*items)
    
    override suspend fun insertPrivateNotificationList(items: List<CachedPrivateNotification>)  =
        privateNotificationsDao.insert(items)
    
    override suspend fun deleteNotificationById(id: String) =
        notificationsDao.deleteById(id)
    
    override suspend fun deletePrivateNotificationById(id: String) =
        privateNotificationsDao.deleteById(id)
    
    override suspend fun getNotificationById(id: String): CachedNotifications? =
        notificationsDao.getById(id)
    
    override suspend fun deleteAllNotifications() =
        notificationsDao.clear()
    
    override suspend fun deleteAllPrivateNotifications(isPrivate: Boolean) =
        privateNotificationsDao.deleteByType(isPrivate)
    
    override suspend fun readNotificationByIds(id: String) = notificationsDao.isReadByIds(id)
    override suspend fun readPrivateNotification(id: String) =
        privateNotificationsDao.readPrivateNotification(id)
    
    override suspend fun readAllPrivateNotifications() =
        privateNotificationsDao.readAllPrivateNotifications()
    
    override fun latestNotification(): Flow<CachedLatestNotifications?> =
        latestNotificationsDao.getLatestNotification()
    
    override suspend fun insertLatestNotification(item: CachedLatestNotifications) =
        latestNotificationsDao.insert(item)
    
    override suspend fun setLatestNotificationAsShown() =
        latestNotificationsDao.setLatestNotificationAsShown()
    
    override suspend fun setAllNotificationsAsRead() =
        latestNotificationsDao.setAllNotificationsAsRead()
    
    override suspend fun clearLatestNotification() =
        latestNotificationsDao.clear()
}