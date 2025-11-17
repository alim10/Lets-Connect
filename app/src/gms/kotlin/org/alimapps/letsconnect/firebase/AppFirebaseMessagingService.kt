package org.alimapps.letsconnect.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject
import kotlin.math.abs

class AppFirebaseMessagingService: FirebaseMessagingService() {

    @Inject
    lateinit var notificationsRepository: INotificationMediator

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val latency = abs(System.currentTimeMillis() - remoteMessage.sentTime)
        val id = remoteMessage.messageId

        Log.d(TAG, "From: ${remoteMessage.from}")
        notificationsRepository.onMessageDataObject(data = remoteMessage.data)
        notificationsRepository.onMessageNotificationObject(remoteMessage = remoteMessage, applicationContext)
    }


    /**
     * Called if the FCM registration token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the
     * FCM registration token is initially generated so this is where you would retrieve the token.
     */
    override fun onNewToken(token: String) {
        notificationsRepository.onRefreshTokenOption(token)
    }
    companion object {
       const val TAG = "AppFirebaseMessagingService"
    }
}