package org.alimapps.letsconnect.data.utils

import android.content.Context
import org.alimapps.letsconnect.core.utils.toGson
import org.alimapps.letsconnect.core.logging.debug
import org.alimapps.letsconnect.notification.manager.data.Notification

object NotificationFactory {
    
    fun get(notification: Notification, context: Context): NotificationAction? {
        debug("RemoteNotification: ${notification.toGson()}")
        return when (notification.reference) {
            else -> null
        }
    }
}
