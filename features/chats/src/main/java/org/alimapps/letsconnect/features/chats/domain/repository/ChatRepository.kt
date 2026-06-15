package org.alimapps.letsconnect.features.chats.domain.repository

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.features.chats.domain.model.Chat
import org.alimapps.letsconnect.features.chats.domain.model.Message

interface ChatRepository {
    fun getChats(): Flow<List<Chat>>
    fun getMessages(chatId: String): Flow<List<Message>>
    suspend fun sendMessage(chatId: String, text: String, isFromMe: Boolean, imageUrl: String? = null)
    suspend fun insertSampleChats(chats: List<Chat>)
}
