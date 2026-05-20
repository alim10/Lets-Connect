package org.alimapps.letsconnect.core.analytics.event

sealed class AppEvent {
    object Logout : AppEvent()
    object OpenDrawer : AppEvent()
    object OpenNafathBottomSheet : AppEvent()
    data class SetToolbarTitle(val title: String) : AppEvent()
}