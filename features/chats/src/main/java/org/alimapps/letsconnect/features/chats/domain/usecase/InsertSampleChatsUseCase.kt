package org.alimapps.letsconnect.features.chats.domain.usecase

import org.alimapps.letsconnect.features.chats.domain.model.Chat
import org.alimapps.letsconnect.features.chats.domain.repository.ChatRepository
import javax.inject.Inject

class InsertSampleChatsUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chats: List<Chat>) {
        repository.insertSampleChats(chats)
    }
}
