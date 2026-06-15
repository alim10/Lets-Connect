package org.alimapps.letsconnect.features.chats.domain.usecase

import org.alimapps.letsconnect.features.chats.domain.repository.ChatRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(chatId: String, text: String, isFromMe: Boolean, imageUrl: String? = null) {
        repository.sendMessage(chatId, text, isFromMe, imageUrl)
    }
}
