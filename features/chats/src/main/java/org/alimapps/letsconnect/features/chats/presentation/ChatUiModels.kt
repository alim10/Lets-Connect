package org.alimapps.letsconnect.features.chats.presentation

data class ChatsItem(
    val id: String,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0,
    val imageUrl: String? = null
)

data class ChatMessage(
    val id: String,
    val text: String,
    val time: String,
    val isFromMe: Boolean,
    val imageUrl: String? = null
)
