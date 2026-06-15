package org.alimapps.letsconnect.features.chats.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.alimapps.letsconnect.core.network.repository.IRemoteConfigRepository
import org.alimapps.letsconnect.features.chats.navigation.BottomNavItem
import org.alimapps.letsconnect.features.chats.navigation.BottomNavigationBar
import org.alimapps.letsconnect.features.profile.presentation.ProfileScreen
import org.alimapps.letsconnect.core.ui.util.StatusBarVisibility

@Composable
fun MainFeatureScreen(remoteConfig: IRemoteConfigRepository) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    StatusBarVisibility(visible = remoteConfig.isStatusBarVisible())
    
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        bottomBar = { 
            val showBottomBar = currentRoute in listOf(
                BottomNavItem.Chat.route,
                BottomNavItem.Call.route,
                BottomNavItem.Profile.route
            )
            if (showBottomBar) {
                BottomNavigationBar(navController = navController, remoteConfig = remoteConfig) 
            }
        }
    ) { innerPadding ->
        
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Chat.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Chat.route) {
                ChatsScreen(
                    onChatClick = { chat ->
                        val encodedUrl = java.net.URLEncoder.encode(chat.imageUrl ?: "", "UTF-8")
                        navController.navigate("chat_detail/${chat.id}/${chat.name}?imageUrl=$encodedUrl")
                    }
                )
            }
            
            composable(
                route = "chat_detail/{chatId}/{chatName}?imageUrl={imageUrl}",
                arguments = listOf(
                    navArgument("chatId") { type = NavType.StringType },
                    navArgument("chatName") { type = NavType.StringType },
                    navArgument("imageUrl") { 
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    }
                )
            ) {
                ChatDetailScreen(
                    onBackClick = { navController.popBackStack() }
                )
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
