package com.alim.letsconnect.notification

import kotlinx.coroutines.flow.Flow

interface NotificationsRemote {

    fun getFcmToken(): Flow<String>

    fun deleteNotification(
        nationalId: String,
        deleteNotificationId: String
    ): Flow<Unit>

    fun deleteAllNotifications(isPrivate: Boolean): Flow<Unit>

    fun readNotification(nationalId: String, readNotification: String): Flow<Unit>

}
