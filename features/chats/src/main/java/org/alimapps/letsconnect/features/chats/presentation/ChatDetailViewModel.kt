package org.alimapps.letsconnect.features.chats.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.alimapps.letsconnect.features.chats.domain.model.Message
import org.alimapps.letsconnect.features.chats.domain.usecase.GetMessagesUseCase
import org.alimapps.letsconnect.features.chats.domain.usecase.SendMessageUseCase
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    private val chatId: String = checkNotNull(savedStateHandle["chatId"])
    private val chatName: String = savedStateHandle.get<String>("chatName") ?: "Chat"
    private val imageUrl: String? = savedStateHandle["imageUrl"]

    private val _messageText = MutableStateFlow("")
    
    val uiState: StateFlow<ChatDetailUiState> = combine(
        getMessagesUseCase(chatId).map { messages -> messages.map { it.toPresentation() } },
        _messageText
    ) { messages, text ->
        ChatDetailUiState(
            chatName = chatName,
            imageUrl = imageUrl,
            messages = messages,
            messageText = text
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ChatDetailUiState(chatName = chatName, imageUrl = imageUrl)
    )

    fun onMessageTextChanged(newText: String) {
        _messageText.value = newText
    }

    fun sendMessage() {
        val currentText = _messageText.value
        if (currentText.isNotBlank()) {
            viewModelScope.launch {
                sendMessageUseCase(chatId, currentText, true)
                _messageText.value = ""
            }
        }
    }
}

data class ChatDetailUiState(
    val chatName: String = "",
    val imageUrl: String? = null,
    val messages: List<ChatMessage> = emptyList(),
    val messageText: String = ""
)

fun Message.toPresentation() = ChatMessage(id, text, time, isFromMe, imageUrl)
