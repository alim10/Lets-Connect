package org.alimapps.letsconnect.features.chats.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.features.chats.domain.model.Message
import org.alimapps.letsconnect.features.chats.domain.repository.ChatRepository
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(chatId: String): Flow<List<Message>> = repository.getMessages(chatId)
}
