package org.alimapps.letsconnect.notification

import android.app.NotificationManager
import androidx.annotation.StringRes

enum class Channel(
    val id: String,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    val importance: Int = NotificationManager.IMPORTANCE_DEFAULT,
) {
    MEDICATION(
        Constants.NOTIFICATIONS.MEDICATION_CHANNEL_ID,
        com.lean.ui.R.string.title_medications,
        com.lean.ui.R.string.my_medications_service_description,
        NotificationManager.IMPORTANCE_HIGH
    ),
    GENERAL(
        Constants.NOTIFICATIONS.GENERAL_CHANNEL_ID,
        com.lean.ui.R.string.title_general,
        com.lean.ui.R.string.general_service_description,
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
