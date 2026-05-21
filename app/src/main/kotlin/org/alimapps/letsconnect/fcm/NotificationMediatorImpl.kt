package org.alimapps.letsconnect.fcm

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.core.os.bundleOf
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.alimapps.letsconnect.core.analytics.Analytics
import org.alimapps.letsconnect.core.analytics.AnalyticsHelper
import org.alimapps.letsconnect.core.common.logging.debug
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.core.common.di.coroutines.IoDispatcher
import org.alimapps.letsconnect.notification.manager.NotificationsManager
import org.alimapps.letsconnect.notification.NotificationsRepository
import org.alimapps.letsconnect.notification.showNotification
import javax.inject.Inject
import kotlin.math.abs
import kotlin.random.Random

@SuppressLint("LogNotTimber")
class NotificationMediatorImpl
@Inject
constructor(
    private val appPrefs: SharedPrefsRepository,
    private val analytics: Analytics,
    private val notificationsManager: NotificationsManager,
    private val notificationsRepository: NotificationsRepository,
    @param:IoDispatcher private val io: CoroutineDispatcher
): INotificationMediator {

    private val supervisorJob by lazy { SupervisorJob() }
    private val coroutineScope by lazy { CoroutineScope(supervisorJob) }

    override fun onRefreshTokenOption(newToken: String) {
        Log.d(TAG, "Refreshed token: $newToken")
        if (appPrefs.accessToken == null || appPrefs.accessToken.isNullOrEmpty()) return
        notificationsRepository.registerFcmToken(newToken)
            .onEach { debug("new Refreshed token registered successfully ") }
            .catch { debug("Can't register new generated token: ${it.message}") }
            .flowOn(io)
            .launchIn(coroutineScope)
    }

    override fun onMessageNotificationObjectOption(remoteMessage: RemoteMessage, applicationContext: Context) {
        sendAnalyticsMessage(remoteMessage)
        remoteMessage.notification?.let {
            Log.d(TAG, "Message Notification Title: ${it.title}")
            Log.d(TAG, "Message Notification Body: ${it.body}")
            val data = remoteMessage.data.toMap<String, String?>()
            val notificationId = Random.nextInt(1, 1000)
//            val notification = notificationsManager.getNotificationObject(data).toNotification(it.title, it.body)
//            notificationsManager.handlePushNotification(notification)
//            val deepLinkNotification = NotificationFactory.get(notification, applicationContext)
            when {
                /*deepLinkNotification != null -> showNotification(
                    applicationContext,
                    title = it.title,
                    message = it.body,
                    pendingIntent = deepLinkNotification.pendingIntent(
                        MainActivity::class.java,
                        R.navigation.navigation_main
                    ),
                    channelId = Channel.GENERAL.id
                )

                remoteMessage.data.toString()
                    .contains(Constants.NOTIFICATIONS.REFERENCE_TELEHEALTH_CONSULTATIONS) -> {
                    showTelehealthConsultationNotification(
                        applicationContext,
                        it.title,
                        it.body
                    )
                }*/



                else -> showNotification(applicationContext, it.title, it.body, notificationId = notificationId)
            }
        }
    }

    override fun onMessageDataObjectOption(data: Map<String, String>, applicationContext: Context) {
        if (!appPrefs.isLoggedIn) return
        if (data.isNotEmpty()) {
            Log.d(TAG, "Message data payload: $data")
            notificationsManager.handleEvent(data, applicationContext)
        }
    }

    private fun sendAnalyticsMessage(remoteMessage: RemoteMessage) {
        val latency = abs(System.currentTimeMillis() - remoteMessage.sentTime)
        val id = remoteMessage.messageId
        Log.d(
            TAG,
            "currentTimeMillis: ${System.currentTimeMillis()} - fcmSentTime: ${remoteMessage.sentTime} = latency: $latency"
        )
        analytics.logCustomEvent(
            AnalyticsHelper.Events.FCM_LATENCY,
            bundleOf(
                AnalyticsHelper.Params.FCM_MSG_ID to id,
                AnalyticsHelper.Params.FCM_MSG_LATENCY to latency
            )
        )
    }

    companion object {
        const val TAG = "MyFirebaseMessaging"
        private const val NOTIFICATION_TYPE = "type"
        private const val NOTIFICATION_TELEHEALTH_CALL = "call"
    }
}