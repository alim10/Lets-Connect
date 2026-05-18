package org.alimapps.letsconnect.notification

import android.app.NotificationManager
import androidx.annotation.StringRes
import org.alimapps.letsconnect.R

enum class Channel(
    val id: String,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    val importance: Int = NotificationManager.IMPORTANCE_DEFAULT,
) {
    MEDICATION(
        Constants.NOTIFICATIONS.MEDICATION_CHANNEL_ID,
        R.string.title_medications,
        R.string.notification_desc,
        NotificationManager.IMPORTANCE_HIGH
    ),
    GENERAL(
        Constants.NOTIFICATIONS.GENERAL_CHANNEL_ID,
        R.string.title_general,
        R.string.general_service_description,
        NotificationManager.IMPORTANCE_HIGH
    ),
}

object NOTIFICATIONS {
    const val GENERAL_CHANNEL_ID = "general_channel_id"
    const val MEDICATION_CHANNEL_ID = "medication_channel_id"

    const val REFERENCE_TELEHEALTH_CONSULTATIONS = "SHY-APT-CNS-U"
    const val REFERENCE_TEAM_CARE = "SHY-TBC-CHG-U"
    const val REFERENCE_COMPANION_RESPONSE = "SEH.TLH.COM.C"
}
