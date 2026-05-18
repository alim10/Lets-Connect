package org.alimapps.letsconnect.fcm
import android.content.Context
import com.google.firebase.messaging.RemoteMessage

interface INotificationMediator {
    fun onRefreshTokenOption(newToken: String)
    fun onMessageNotificationObjectOption(remoteMessage: RemoteMessage, applicationContext: Context)
    fun onMessageDataObjectOption(data: Map<String, String>, applicationContext: Context)
}