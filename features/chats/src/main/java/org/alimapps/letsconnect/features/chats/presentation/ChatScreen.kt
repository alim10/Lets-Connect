package org.alimapps.letsconnect.features.chats.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class ChatItem(
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0
)

@Composable
fun ChatScreen() {
    val chats = listOf(
        ChatItem("John Doe", "Hey, how are you?", "10:30 AM", 2),
        ChatItem("Jane Smith", "Meeting at 2 PM", "Yesterday", 0),
        ChatItem("Family Group", "Mom: Happy Birthday!", "Monday", 5),
        ChatItem("Work Buddies", "Bob sent a photo", "12/05/2024", 0)
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Chats",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(chats) { chat ->
                ChatListItem(chat)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color.LightGray)
            }
        }
    }
}

@Composable
fun ChatListItem(chat: ChatItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            color = Color.Gray
        ) {}
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = chat.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = chat.time, fontSize = 12.sp, color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = chat.lastMessage,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    maxLines = 1
                )
                if (chat.unreadCount > 0) {
                    Surface(
                        color = Color(0xFF25D366),
                        shape = CircleShape,
                        modifier = Modifier.size(20.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                text = chat.unreadCount.toString(),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
