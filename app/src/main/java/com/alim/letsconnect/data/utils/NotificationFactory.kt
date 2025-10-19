package com.alim.letsconnect.data.utils

import android.content.Context
import com.alim.letsconnect.core.utils.toGson
import com.alim.letsconnect.core.logging.debug
import com.alim.letsconnect.notification.manager.data.Notification

object NotificationFactory {
    
    fun get(notification: Notification, context: Context): NotificationAction? {
        debug("RemoteNotification: ${notification.toGson()}")
        return when (notification.reference) {
            else -> null
        }
    }
}
