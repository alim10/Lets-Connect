package com.alim.letsconnect.notification

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.alim.letsconnect.R
import com.alim.letsconnect.presentation.ui.MainActivity

/**
 * Created by Ahmed Ibrahim on 01,April,2021
 */
fun showNotification(
    context: Context,
    title: String?,
    message: String?,
    notificationId: Int = 0,
    channelId: String = Constants.NOTIFICATIONS.GENERAL_CHANNEL_ID,
    bundle: Bundle? = null,
    action: String? = null
) {
    // Create Pending intent
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        bundle?.let { putExtras(it) }
        action?.let { this.action = action }
    }
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        else PendingIntent.FLAG_ONE_SHOT
    )
    
    showNotification(
        context = context,
        title = title,
        message = message,
        notificationId = notificationId,
        channelId = channelId,
        pendingIntent = pendingIntent
    )
}

@SuppressLint("MissingPermission")
fun showNotification(
    context: Context,
    title: String?,
    message: String?,
    notificationId: Int = 0,
    channelId: String = Channel.GENERAL.id,
    pendingIntent: PendingIntent
) {
    // Create the notification
    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setColor(context.resources.getColor(com.alim.letsconnect.core.R.color.color_accent))
        .setContentTitle(title)
        .setContentText(message)
        .setAutoCancel(true)
        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM))
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setVibrate(LongArray(0))
    
    with(NotificationManagerCompat.from(context)) {
        // notificationId is a unique int for each notification that you must define
        notify(notificationId, builder.build())
    }
}

fun showCampaignNotification(context: Context, title: String?, message: String?, url: String) {
    // Create Pending intent
    val uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, uri)
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
        else PendingIntent.FLAG_ONE_SHOT
    )
    
    showNotification(
        context = context,
        title = title,
        message = message,
        pendingIntent = pendingIntent,
        channelId = Channel.GENERAL.id
    )
}

fun createAppChannels(app: Application) {
    Channel.values().forEach { channel ->
        createChannel(app, channel)
    }
}

fun createChannel(app: Application, channel: Channel) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel = NotificationChannel(
            channel.id,
            app.getString(channel.nameRes), channel.importance
        )
        notificationChannel.description = app.getString(channel.descriptionRes)
        val notificationManager = app.getSystemService(Context.NOTIFICATION_SERVICE)
            as? NotificationManager
        notificationManager?.createNotificationChannel(notificationChannel)
    }
}
