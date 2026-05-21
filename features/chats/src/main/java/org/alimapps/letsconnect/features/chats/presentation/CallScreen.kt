package org.alimapps.letsconnect.features.chats.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class CallItem(
    val name: String,
    val date: String,
    val isMissed: Boolean = false
)

@Composable
fun CallScreen() {
    val calls = listOf(
        CallItem("John Doe", "Today, 10:45 AM"),
        CallItem("Jane Smith", "Yesterday, 8:20 PM", true),
        CallItem("Bob", "May 12, 11:30 AM")
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Calls",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp),
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(calls) { call ->
                CallListItem(call)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color.LightGray)
            }
        }
    }
}

@Composable
fun CallListItem(call: CallItem) {
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
            Text(
                text = call.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = if (call.isMissed) Color.Red else Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = call.date, fontSize = 14.sp, color = Color.Gray)
        }
        
        IconButton(onClick = { /* TODO */ }) {
            Icon(Icons.Default.Call, contentDescription = "Call", tint = Color(0xFF075E54))
        }
    }
}
