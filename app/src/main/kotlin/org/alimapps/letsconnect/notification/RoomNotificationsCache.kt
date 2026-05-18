package org.alimapps.letsconnect.notification

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomNotificationsCache @Inject constructor(
//    appDatabase: AppDatabase,
//    private val appPrefs: AppPreference
) : NotificationsCache {
    override fun isFcmTokenRegistered(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun setFcmTokenRegistered(isRegistered: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNotificationById(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePrivateNotificationById(id: String) {
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