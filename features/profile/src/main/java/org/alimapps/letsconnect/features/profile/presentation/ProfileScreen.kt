package org.alimapps.letsconnect.features.profile.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SettingItem(
    val title: String,
    val subtitle: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)

@Composable
fun ProfileScreen() {
    var showSettings by remember { mutableStateOf(false) }

    if (showSettings) {
        SettingScreen(onBack = { showSettings = false })
    } else {
        ProfileContent(onSettingsClick = { showSettings = true })
    }
}

@Composable
fun ProfileContent(onSettingsClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Profile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )

        // Profile Detail section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                color = Color.LightGray
            ) {}
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Your Name", fontWeight = FontWeight.Bold, fontSize = 22.sp)
                Text(text = "Hey there! I am using LetsConnect", fontSize = 14.sp, color = Color.Gray)
            }
        }

        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

        CustomListItem(
            headlineContent = { Text("Settings", fontWeight = FontWeight.Medium) },
            supportingContent = { Text("Privacy, security, language", fontSize = 14.sp, color = Color.Gray) },
            leadingContent = { Icon(Icons.Default.Settings, contentDescription = null, tint = Color.Gray) },
            modifier = Modifier.fillMaxWidth(),
            trailingContent = { Icon(Icons.Default.ChevronRight, contentDescription = null) },
            onClick = onSettingsClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(onBack: () -> Unit) {
    val settings = listOf(
        SettingItem("Account", "Privacy, security, change number", Icons.Default.AccountCircle),
        SettingItem("Chats", "Theme, wallpapers, chat history", Icons.AutoMirrored.Filled.Chat),
        SettingItem("Notifications", "Message, group & call tones", Icons.Default.Notifications),
        SettingItem("Storage and Data", "Network usage, auto-download", Icons.Default.DataUsage),
        SettingItem("Help", "Help center, contact us, privacy policy", Icons.Default.Info)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Settings") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        )

        LazyColumn {
            items(settings) { item ->
                CustomListItem(
                    headlineContent = { Text(item.title, fontWeight = FontWeight.Medium) },
                    supportingContent = { Text(item.subtitle, fontSize = 14.sp, color = Color.Gray) },
                    leadingContent = { Icon(item.icon, contentDescription = null, tint = Color.Gray) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun CustomListItem(
    headlineContent: @Composable () -> Unit,
    supportingContent: @Composable () -> Unit,
    leadingContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    trailingContent: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    Surface(onClick = onClick) {
        Row(
            modifier = modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingContent()
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                headlineContent()
                supportingContent()
            }
            trailingContent?.invoke()
        }
    }
}
