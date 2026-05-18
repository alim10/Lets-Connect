package org.alimapps.letsconnect.data.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavDeepLinkBuilder
import org.alimapps.letsconnect.R

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
    open val iconRes: Int = R.drawable.ic_launcher_background
    
    @get:StringRes
    abstract val btnPositive: Int?
    
    @StringRes
    open val btnNegative: Int? = null
}