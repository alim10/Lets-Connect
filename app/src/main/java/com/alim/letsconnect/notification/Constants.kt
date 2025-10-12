package com.alim.letsconnect.notification

object Constants {
    const val NOTIFICATION_HEADERS_KEY = "Headers"
    const val NOTIFICATION_KEY_KEY = "Key"
    const val NOTIFICATION_VALUE_KEY = "Value"
    const val NOTIFICATION_TIMESTAMP_KEY = "Timestamp"
    const val NOTIFICATION_TYPE_KEY = "Type"
    const val PRIVATE_NOTIFICATION_TYPE_VALUE = "PR"
    const val TARGET_NATIONAL_ID = "TargetNationalID"


    object NOTIFICATIONS {
        const val GENERAL_CHANNEL_ID = "general_channel_id"
        const val MEDICATION_CHANNEL_ID = "medication_channel_id"

        const val REFERENCE_TELEHEALTH_CONSULTATIONS = "SHY-APT-CNS-U"
        const val REFERENCE_TEAM_CARE = "SHY-TBC-CHG-U"
        const val REFERENCE_COMPANION_RESPONSE = "SEH.TLH.COM.C"
    }
}