package org.alimapps.letsconnect.notification

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.data.AppDatabase
import org.alimapps.letsconnect.notification.local.model.CachedLatestNotifications
import org.alimapps.letsconnect.notification.local.model.CachedNotifications
import org.alimapps.letsconnect.notification.local.model.CachedPrivateNotification
import org.alimapps.letsconnect.notification.local.source.NotificationsCache
import javax.inject.Inject

class RoomNotificationsCache @Inject constructor(
    appDatabase: AppDatabase,
    private val appPrefs: SharedPrefsRepository
) : NotificationsCache {
    override fun isFcmTokenRegistered(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun setFcmTokenRegistered(isRegistered: Boolean) {
        TODO("Not yet implemented")
    }

    override fun getNotificationsList(): Flow<List<CachedNotifications>?> {
        TODO("Not yet implemented")
    }

    override fun getPrivateNotificationsList(isPrivate: Boolean): Flow<List<CachedPrivateNotification>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertNotificationList(vararg items: CachedNotifications) {
        TODO("Not yet implemented")
    }

    override suspend fun insertPrivateNotificationList(items: List<CachedPrivateNotification>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotificationById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePrivateNotificationById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getNotificationById(id: String): CachedNotifications? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllNotifications() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAllPrivateNotifications(isPrivate: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun readNotificationByIds(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readPrivateNotification(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun readAllPrivateNotifications() {
        TODO("Not yet implemented")
    }

    override fun latestNotification(): Flow<CachedLatestNotifications?> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLatestNotification(item: CachedLatestNotifications) {
        TODO("Not yet implemented")
    }

    override suspend fun setLatestNotificationAsShown() {
        TODO("Not yet implemented")
    }

    override suspend fun setAllNotificationsAsRead() {
        TODO("Not yet implemented")
    }

    override suspend fun clearLatestNotification() {
        TODO("Not yet implemented")
    }
}