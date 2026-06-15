package org.alimapps.letsconnect.features.chats.domain.model

data class Chat(
    val id: String,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0,
    val imageUrl: String? = null
)

data class Message(
    val id: String,
    val text: String,
    val time: String,
    val isFromMe: Boolean,
    val imageUrl: String? = null
)
