package org.alimapps.letsconnect.features.chats.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import org.alimapps.letsconnect.core.network.repository.IRemoteConfigRepository

@Composable
fun BottomNavigationBar(navController: NavController, remoteConfig: IRemoteConfigRepository) {
    val items = mutableListOf<BottomNavItem>()
    if (remoteConfig.isChatTabEnabled()) items.add(BottomNavItem.Chat)
    if (remoteConfig.isCallTabEnabled()) items.add(BottomNavItem.Call)
    if (remoteConfig.isProfileTabEnabled()) items.add(BottomNavItem.Profile)

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
