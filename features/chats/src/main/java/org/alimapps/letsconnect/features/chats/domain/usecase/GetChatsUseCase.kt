package org.alimapps.letsconnect.features.chats.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.alimapps.letsconnect.features.chats.domain.model.Chat
import org.alimapps.letsconnect.features.chats.domain.repository.ChatRepository
import javax.inject.Inject

class GetChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    operator fun invoke(): Flow<List<Chat>> = repository.getChats()
}
