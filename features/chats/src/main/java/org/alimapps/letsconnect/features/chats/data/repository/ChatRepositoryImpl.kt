package org.alimapps.letsconnect.features.chats.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alimapps.letsconnect.core.database.dao.ChatDao
import org.alimapps.letsconnect.core.database.entity.ChatEntity
import org.alimapps.letsconnect.core.database.entity.MessageEntity
import org.alimapps.letsconnect.features.chats.domain.model.Chat
import org.alimapps.letsconnect.features.chats.domain.model.Message
import org.alimapps.letsconnect.features.chats.domain.repository.ChatRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao
) : ChatRepository {

    override fun getChats(): Flow<List<Chat>> = chatDao.getChats().map { entities ->
        entities.map { it.toDomain() }
    }

    override fun getMessages(chatId: String): Flow<List<Message>> {
        return chatDao.getMessages(chatId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun sendMessage(chatId: String, text: String, isFromMe: Boolean, imageUrl: String?) {
        val message = MessageEntity(
            id = System.currentTimeMillis().toString(),
            chatId = chatId,
            text = text,
            time = "Now",
            isFromMe = isFromMe,
            imageUrl = imageUrl
        )
        chatDao.insertMessage(message)
    }

    override suspend fun insertSampleChats(chats: List<Chat>) {
        chatDao.insertChats(chats.map { it.toEntity() })
    }
}

// Mappers
fun ChatEntity.toDomain() = Chat(id, name, lastMessage, time, unreadCount, imageUrl)
fun Chat.toEntity() = ChatEntity(id, name, lastMessage, time, unreadCount, imageUrl)
fun MessageEntity.toDomain() = Message(id, text, time, isFromMe, imageUrl)
