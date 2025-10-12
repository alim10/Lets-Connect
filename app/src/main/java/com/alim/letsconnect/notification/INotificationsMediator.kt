package com.alim.letsconnect.notification

import android.content.Context
import com.google.firebase.messaging.RemoteMessage

interface INotificationsMediator {
    fun onRefreshTokenOption(newToken: String)
    fun onMessageNotificationObject(remoteMessage: RemoteMessage, applicationContext: Context)
    fun onMessageDataObject(data: Map<String, String>)
}