package com.alim.letsconnect.notification

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import com.alim.letsconnect.di.coroutines.ApplicationScope
import com.alim.letsconnect.notification.Constants.NOTIFICATION_HEADERS_KEY
import com.alim.letsconnect.notification.Constants.NOTIFICATION_KEY_KEY
import com.alim.letsconnect.notification.Constants.NOTIFICATION_TIMESTAMP_KEY
import com.alim.letsconnect.notification.Constants.NOTIFICATION_TYPE_KEY
import com.alim.letsconnect.notification.Constants.NOTIFICATION_VALUE_KEY
import com.alim.letsconnect.notification.Constants.TARGET_NATIONAL_ID
import com.alim.letsconnect.notification.manager.data.Headers
import com.alim.letsconnect.notification.manager.data.Notification
import com.alim.letsconnect.notification.manager.data.NotificationSuperObject
import com.alim.letsconnect.notification.manager.data.Timestamp
import com.alim.letsconnect.common.toModel
import com.alim.letsconnect.common.toObject
import kotlinx.coroutines.CoroutineScope
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationsManager @Inject constructor() {

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
