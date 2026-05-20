package org.alimapps.letsconnect.core.common.notification

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavDeepLinkBuilder
import org.alimapps.letsconnect.core.common.R
import org.alimapps.letsconnect.core.common.navigation.DeepLinkDestination

abstract class NotificationAction(private val context: Context) {
    
    fun pendingIntent(activityName: Class<out Activity?>, graphId: Int) =
        NavDeepLinkBuilder(context)
            .setComponentName(activityName)
            .setGraph(graphId)
            .setDestination(destinationId)
            .setArguments(bundle)
            .createPendingIntent()
    
    abstract val deepLinkDestination: DeepLinkDestination
    
    open fun onPositiveAction(id: String): DeepLinkDestination = deepLinkDestination
    
    open fun onNegativeAction(id: String): DeepLinkDestination? = null
    
    abstract val destinationId: Int
    
    abstract val bundle: Bundle?
    
    @DrawableRes
    open val iconRes: Int = R.drawable.ic_notification_bell
    
    @get:StringRes
    abstract val btnPositive: Int?
    
    @StringRes
    open val btnNegative: Int? = null
}