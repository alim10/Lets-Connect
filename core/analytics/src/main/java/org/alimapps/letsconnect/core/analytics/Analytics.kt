package org.alimapps.letsconnect.core.analytics

import android.os.Bundle

/**
 * API for app analytics
 *
 * Easy to apply for any Provider [FirebaseAnalytics, etc...]
 */
interface Analytics {
    /** initialize the sdk if it required*/

    fun initialize(params: Map<String, String>)
    
    /** Record current screen**/
    fun logCurrentScreen(screenName: String)

    /** Record any UI events [Clicks, Navigation etc...]**/
    fun logUiEvent(itemId: String, action: String)
    
    /** Record any UI events [Clicks, Navigation etc...]**/
    fun logEvent(eventName: String)

    /** Record custom event with [params] as bundle **/
    fun logCustomEvent(eventName: String, params: Bundle)
}

object AnalyticsHelper {
    object Events {
        const val SCREEN_OPEN = "ScreenOpen"
        const val MILESTONE = "Milestone"
        const val USER_ACTION = "UserAction"
        const val SERVER_ERROR = "ServerError"
        const val FCM_LATENCY = "FCM-Latency"
    }

    object Params {
        const val SCREEN = "screen"
        const val FLOW = "Flow"
        const val STEP = "Step"
        const val EVENT = "event"
        const val FCM_MSG_ID = "Message ID"
        const val FCM_MSG_LATENCY = "Latency"
        const val ANALYTICS_ID = "ANALYTICS_ID"
    }

    object Values {
        const val PERSONAL_INFORMATION = "Personal Information"
        const val REGISTRATION = "Registration"
        const val FORGOT_PASSWORD = "ForgotPassword"
        const val RESET_PASSWORD = "Reset password"
        const val BIOMETRIC_ENABLED = "Biometric Enabled"
        const val FLOW_LOGIN = "Login"
        const val FLOW_SETTINGS = "Settings"
        const val FLOW_SIDE_MENU = "SideMenu"
        const val FLOW_TEAM_CARE = "TeamCare"
        const val TAP = "Tap"
    }
    object DINamed {
        const val AMPLITUDE_ANALYTICS_DI = "AMPLITUDE_ANALYTICS_DI"
    }
    
    object AppointmentsAnalyticsConstants {
        const val screen_Index_appointments_upcoming = "screen_Index_appointments_upcoming"
        const val screen_Index_appointments_past = "screen_Index_appointments_past"
        const val action_Index_appointments_deps_filter = "action_Index_appointments_deps_filter"
        const val action_Index_appointments_booking_appointments = "action_Index_appointments_booking_appointments"
    }
}