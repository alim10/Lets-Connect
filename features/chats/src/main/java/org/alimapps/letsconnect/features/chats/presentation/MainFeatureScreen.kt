package org.alimapps.letsconnect.features.chats.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.alimapps.letsconnect.core.remoteconfig.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.features.chats.navigation.BottomNavItem
import org.alimapps.letsconnect.features.chats.navigation.BottomNavigationBar
import org.alimapps.letsconnect.features.profile.presentation.ProfileScreen
import org.alimapps.letsconnect.core.ui.util.StatusBarVisibility

@Composable
fun MainFeatureScreen(remoteConfig: IRemoteConfigRepository) {
    val navController = rememberNavController()
    StatusBarVisibility(visible = remoteConfig.isStatusBarVisible())
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = { BottomNavigationBar(navController = navController, remoteConfig = remoteConfig) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Chat.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Chat.route) {
                ChatScreen()
            }
            composable(BottomNavItem.Call.route) {
                CallScreen()
            }
            composable(BottomNavItem.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
