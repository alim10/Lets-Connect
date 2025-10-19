package com.alim.letsconnect.core.event

sealed class AppEvent {
    object Logout : AppEvent()
    object OpenDrawer : AppEvent()
}