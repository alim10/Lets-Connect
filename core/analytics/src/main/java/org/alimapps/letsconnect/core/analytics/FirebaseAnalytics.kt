package org.alimapps.letsconnect.core.analytics
import android.os.Bundle
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.FirebaseAnalytics.Event.SCREEN_VIEW
import com.google.firebase.analytics.FirebaseAnalytics.Event.SELECT_ITEM
import com.google.firebase.analytics.FirebaseAnalytics.Param.CONTENT_TYPE
import com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_ID
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

internal class FirebaseAnalytics: Analytics {

    private var firebaseAnalytics: FirebaseAnalytics? = null

    private var coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    
    init {
        initialize(mapOf(AnalyticsHelper.Params.ANALYTICS_ID to ""))
    }
    
    override fun initialize(params: Map<String, String>) {
        val id = params[AnalyticsHelper.Params.ANALYTICS_ID]
        firebaseAnalytics = Firebase.analytics
        firebaseAnalytics?.setUserId(id)
    }
    override fun logCurrentScreen(screenName: String) {
        firebaseAnalytics?.run {
            Log.d(TAG, "Screen with name $screenName has been recorded")
            logEvent(KEY_LOGGING_SCREEN) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
                param(FirebaseAnalytics.Param.SCREEN_CLASS, screenName)
            }
        }
    }
    override fun logEvent(eventName: String) {
        firebaseAnalytics?.logEvent(SELECT_ITEM) {
            param(KEY_UI_ACTION, eventName)
        }
    }
    override fun logUiEvent(itemId: String, action: String) {
        firebaseAnalytics?.logEvent(SELECT_ITEM) {
            param(ITEM_ID, itemId)
            param(CONTENT_TYPE, KEY_UI_EVENT)
            param(KEY_UI_ACTION, action)
        }
        Log.d(TAG, "Action with ID: $itemId and action: $action has been recorded")
    }

    override fun logCustomEvent(eventName: String, params: Bundle) {
        coroutineScope.launch {
            firebaseAnalytics?.run { logEvent(eventName, params) }
        }

        Log.d(TAG, "Custom action with event name: $eventName and value: $params has been recorded")
    }

    companion object {
        private const val TAG = "FirebaseAnalytics"

        // Key types
        private const val KEY_TYPE_SCREEN = "screen"
        private const val KEY_LOGGING_SCREEN = "log_screen"
        private const val KEY_UI_EVENT = "ui event"
        private const val KEY_UI_ACTION = "ui action"
    }
}