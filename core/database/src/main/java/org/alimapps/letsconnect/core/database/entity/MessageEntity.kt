package org.alimapps.letsconnect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey val id: String,
    val chatId: String,
    val text: String,
    val time: String,
    val isFromMe: Boolean,
    val imageUrl: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
