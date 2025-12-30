package org.alimapps.letsconnect.core.common.event

sealed class AppEvent {
    object Logout : AppEvent()
    object OpenDrawer : AppEvent()
}