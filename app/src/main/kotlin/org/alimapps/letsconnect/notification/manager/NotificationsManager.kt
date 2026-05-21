package org.alimapps.letsconnect.notification.manager

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.WorkManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.CoroutineDispatcher
import org.alimapps.letsconnect.core.common.di.coroutines.ApplicationScope
import org.alimapps.letsconnect.notification.Constants.NOTIFICATION_HEADERS_KEY
import org.alimapps.letsconnect.notification.Constants.NOTIFICATION_KEY_KEY
import org.alimapps.letsconnect.notification.Constants.NOTIFICATION_TIMESTAMP_KEY
import org.alimapps.letsconnect.notification.Constants.NOTIFICATION_TYPE_KEY
import org.alimapps.letsconnect.notification.Constants.NOTIFICATION_VALUE_KEY
import org.alimapps.letsconnect.notification.Constants.TARGET_NATIONAL_ID
import org.alimapps.letsconnect.notification.manager.data.Headers
import org.alimapps.letsconnect.notification.manager.data.Notification
import org.alimapps.letsconnect.notification.manager.data.NotificationSuperObject
import org.alimapps.letsconnect.notification.manager.data.Timestamp
import org.alimapps.letsconnect.core.common.utils.toModel
import org.alimapps.letsconnect.core.common.utils.toObject
import kotlinx.coroutines.CoroutineScope
import org.alimapps.letsconnect.R
import org.alimapps.letsconnect.core.common.logging.Logger
import org.alimapps.letsconnect.core.session.SharedPrefsRepository
import org.alimapps.letsconnect.data.utils.NotificationFactory
import org.alimapps.letsconnect.core.common.di.coroutines.IoDispatcher
import org.alimapps.letsconnect.notification.INotificationsRepository
import org.alimapps.letsconnect.notification.NOTIFICATIONS.REFERENCE_TELEHEALTH_CONSULTATIONS
import org.alimapps.letsconnect.notification.showNotification
import org.alimapps.letsconnect.presentation.ui.MainActivity
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsManager @Inject constructor(
    private val workManager: WorkManager,
    private val crashlytics: FirebaseCrashlytics,
    private val appPrefs: SharedPrefsRepository,
    private val notificationCenterRepository: INotificationsRepository,
    @IoDispatcher private val io: CoroutineDispatcher,
) {

    @ApplicationScope
    @Inject
    lateinit var coroutineScope: CoroutineScope

    fun handleEvent(
        payload: Map<String, String?>,
    ): Boolean {
        runCatching {
            val notificationSuperObject = getNotificationObject(payload)
            val reference = notificationSuperObject.headers?.reference
            handleNotifications(notificationSuperObject)
            reference.let { t ->
                return false
            }
        }.getOrElse {
            return false
        }
    }

    fun handleEvent(
        payload: Map<String, String?>,
        applicationContext: Context
    ): Boolean {
        runCatching {
            val notificationSuperObject = getNotificationObject(payload)
            val reference = notificationSuperObject.headers?.reference
            val notification = notificationSuperObject.toNotification(payload["title"], payload["body"])
            val deepLinkNotification = NotificationFactory.get(notification, applicationContext)
            handleNotifications(notificationSuperObject)
            reference.let { t ->
                when (t) {
                    REFERENCE_TELEHEALTH_CONSULTATIONS -> {}

                    "REFERENCE" ->  {
                        if (deepLinkNotification != null) {
                            showNotification(
                                context = applicationContext,
                                title = payload["title"].orEmpty(),
                                message = payload["body"].orEmpty(),
                                pendingIntent = deepLinkNotification.pendingIntent(
                                    MainActivity::class.java,
                                    R.navigation.navigation_main
                                ),
                            )
                        } else {
                            Logger.d("Dependents notification deep link is null")
                        }
                    }
                    else -> Logger.d("Reference key is ${t.orEmpty()}")
                }
                return false
            }
        }.getOrElse {
            return false
        }
    }

    private fun handleNotifications(notification: NotificationSuperObject) {
    }


    fun handlePushNotification(notification: Notification) {

    }

    private fun handleDependentPushNotification(notification: Notification) {
        // in case the request has been approved we need to update dependent cache + access token

    }

    /**
     * Build constraints that will keep manager waiting until meet them to fire
     *
     * in our case as a basic constraint => the device should connected to the internet
     */
    private fun getRequiredWorkerConstraints(): Constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    /**
     * Like Bundle we can pass data to the worker
     */
    private fun buildBodyData(key: String, body: String) = Data.Builder()
        .putString(key, body)
        .build()

    companion object {
        private const val SYNC_VIRTUAL_APPOINTMENTS = "sync-virtual-appointments"
        private const val SYNC_TEAM_CARE = "sync-team-care"
    }
}

fun getNotificationObject(remoteData: Map<String, String?>): NotificationSuperObject {
    val jsonObject = JSONObject(remoteData)
    val headersObject = if (jsonObject.has(NOTIFICATION_HEADERS_KEY)) {
        toModel<Headers>(jsonObject.get(NOTIFICATION_HEADERS_KEY).toString())
    } else null

    val keyString = if (jsonObject.has(NOTIFICATION_KEY_KEY)) {
        jsonObject.get(NOTIFICATION_KEY_KEY).toString()
    } else null

    val timestampObject = if (jsonObject.has(NOTIFICATION_TIMESTAMP_KEY)) {
        toModel<Timestamp>(jsonObject.get(NOTIFICATION_TIMESTAMP_KEY).toString())
    } else null

    val value = if (jsonObject.has(NOTIFICATION_VALUE_KEY)) {
        (jsonObject.get(NOTIFICATION_VALUE_KEY) as String).toObject(Map::class.java)
            ?.asSequence()
            ?.associate { (key, value) -> key?.toString() to value?.toString() }
    } else null

    val type = if (jsonObject.has(NOTIFICATION_TYPE_KEY)) {
        jsonObject.getString(NOTIFICATION_TYPE_KEY)
    } else null
    return NotificationSuperObject(
        headersObject,
        keyString,
        timestampObject,
        value,
        type = type,
        targetNationalID = remoteData[TARGET_NATIONAL_ID]
    )
}
