package org.alimapps.letsconnect.features.chats.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Assuming chatId and chatName are passed via navigation arguments
    private val chatId: String = checkNotNull(savedStateHandle["chatId"])
    private val initialChatName: String = savedStateHandle.get<String>("chatName") ?: "Chat"
    private val initialImageUrl: String? = savedStateHandle["imageUrl"]

    private val _uiState = MutableStateFlow(ChatDetailUiState(
        chatName = initialChatName,
        imageUrl = initialImageUrl
    ))
    val uiState: StateFlow<ChatDetailUiState> = _uiState.asStateFlow()

    init {
        loadMessages()
    }

    private fun loadMessages() {
        // In a real app, you'd fetch this from a repository based on chatId
        val initialMessages = listOf(
            ChatMessage("1", "Hey there!", "10:00 AM", false),
            ChatMessage("2", "Hi! How are you?", "10:01 AM", true),
            ChatMessage("3", "I'm good, thanks! Want to grab coffee?", "10:02 AM", false),
            ChatMessage("4", "Sure, what time?", "10:05 AM", true)
        )
        _uiState.update { it.copy(messages = initialMessages) }
    }

    fun onMessageTextChanged(newText: String) {
        _uiState.update { it.copy(messageText = newText) }
    }

    fun sendMessage() {
        val currentText = _uiState.value.messageText
        if (currentText.isNotBlank()) {
            val newMessage = ChatMessage(
                id = System.currentTimeMillis().toString(),
                text = currentText,
                time = "Now",
                isFromMe = true
            )
            _uiState.update { 
                it.copy(
                    messages = it.messages + newMessage,
                    messageText = ""
                )
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

