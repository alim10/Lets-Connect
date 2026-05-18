package org.alimapps.letsconnect.notification

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NotificationsRemoteImpl @Inject constructor() : NotificationsRemote {
    override fun getFcmToken(): Flow<String> = flow { emit("") }

    override fun deleteNotification(nationalId: String, deleteNotificationId: String): Flow<Unit> = flow {
        emit(Unit)
    }

    override fun deleteAllNotifications(isPrivate: Boolean): Flow<Unit> = flow {
        emit(Unit)
    }

    override fun readNotification(nationalId: String, readNotification: String): Flow<Unit> = flow {
        emit(Unit)
    }
}
