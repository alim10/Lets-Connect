package org.alimapps.letsconnect.fcm
import android.annotation.SuppressLint
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("LogNotTimber")
@AndroidEntryPoint
class MyFirebaseMessagingService: FirebaseMessagingService() {
    @Inject
    lateinit var notificationMediator: INotificationMediator
    
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Not getting messages here? See why this may be: https://firebase.google.com/docs/cloud-messaging/android/receive#handling_messages
        Log.e(TAG, """
            Firebase Message Received:
                - From: ${remoteMessage.from}
                - Object/Data: ${remoteMessage.data}
                - Object/Notification: ${remoteMessage.notification}
            """.trimIndent())
        notificationMediator.onMessageNotificationObjectOption(remoteMessage, applicationContext)
        // Check if message contains a data payload.
        notificationMediator.onMessageDataObjectOption(remoteMessage.data, applicationContext)
    }

    override fun onNewToken(token: String) {
        notificationMediator.onRefreshTokenOption(token)
    }

    companion object {
        const val TAG = "MyFirebaseMessaging"
    }
}