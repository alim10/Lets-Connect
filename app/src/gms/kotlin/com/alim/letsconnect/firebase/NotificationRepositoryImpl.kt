package com.alim.letsconnect.firebase

import android.content.Context
import android.util.Log
import com.alim.letsconnect.di.coroutines.IoDispatcher
import com.alim.letsconnect.firebase.AppFirebaseMessagingService.Companion.TAG
import com.alim.letsconnect.notification.NotificationsManager
import com.alim.letsconnect.notification.NotificationsRepository
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationManager: NotificationsManager,
    private val notificationsRepository: NotificationsRepository,
    @IoDispatcher private val io: CoroutineDispatcher
) : INotificationMediator {

    private val supervisorJob by lazy { SupervisorJob() }
    private val coroutineScope by lazy { CoroutineScope(supervisorJob) }

    override fun onRefreshTokenOption(newToken: String) {
        Log.d(TAG, "Refresh Token: $newToken")
        notificationsRepository.registerFcmToken(newToken)
            .onEach {  Log.d(TAG, "New Refresh Token registered : $newToken") }
            .catch {  Log.d(TAG, "New Refresh Token can not registered: ${it.message}") }
            .flowOn(io)
            .launchIn(coroutineScope)
    }

    override fun onMessageNotificationObject(
        remoteMessage: RemoteMessage,
        applicationContext: Context
    ) {
        Log.d(TAG, "From: ${remoteMessage.from}")
        Log.d(TAG, "From: ${remoteMessage.from}")

    }

    override fun onMessageDataObject(data: Map<String, String>) {
        Log.d(TAG, "Message payload: $data")
        if (data.isNotEmpty()) {
            notificationManager.handleEvent(data)
        }
    }
}