package org.alimapps.letsconnect.features.chats.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Chat : BottomNavItem("chat", "Chat", Icons.AutoMirrored.Filled.Chat)
    data object Call : BottomNavItem("call", "Call", Icons.Default.Call)
    data object Profile : BottomNavItem("profile", "Profile", Icons.Default.AccountCircle)
}
