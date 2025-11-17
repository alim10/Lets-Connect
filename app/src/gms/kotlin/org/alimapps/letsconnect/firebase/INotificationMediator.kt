package org.alimapps.letsconnect.firebase

import android.content.Context
import com.google.firebase.messaging.RemoteMessage

interface INotificationMediator {
    fun onRefreshTokenOption(newToken: String)
    fun onMessageNotificationObject(remoteMessage: RemoteMessage, applicationContext: Context)
    fun onMessageDataObject(data: Map<String, String>)
}