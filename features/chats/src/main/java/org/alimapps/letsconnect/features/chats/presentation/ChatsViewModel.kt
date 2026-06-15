package org.alimapps.letsconnect.features.chats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.alimapps.letsconnect.features.chats.domain.model.Chat
import org.alimapps.letsconnect.features.chats.domain.usecase.GetChatsUseCase
import org.alimapps.letsconnect.features.chats.domain.usecase.InsertSampleChatsUseCase
import javax.inject.Inject

@HiltViewModel
class ChatsViewModel @Inject constructor(
    private val getChatsUseCase: GetChatsUseCase,
    private val insertSampleChatsUseCase: InsertSampleChatsUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val chats: StateFlow<List<ChatsItem>> = combine(
        getChatsUseCase().map { chats -> chats.map { it.toPresentation() } },
        _searchQuery
    ) { chats, query ->
        if (query.isBlank()) {
            chats
        } else {
            chats.filter { 
                it.name.contains(query, ignoreCase = true) || 
                it.lastMessage.contains(query, ignoreCase = true) 
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    init {
        // Initial load of sample data if DB is empty
        viewModelScope.launch {
            insertSampleChatsUseCase(
                listOf(
                    Chat("1", "John Doe", "Hey, how are you?", "10:30 AM", 2, "https://i.pravatar.cc/150?u=1"),
                    Chat("2", "Jane Smith", "Meeting at 2 PM", "Yesterday", 0, null),
                    Chat("3", "Family Group", "Mom: Happy Birthday!", "Monday", 5, "https://i.pravatar.cc/150?u=3"),
                    Chat("4", "Work Buddies", "Bob sent a photo", "12/05/2024", 0, "https://i.pravatar.cc/150?u=4")
                )
            )
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}

// Mapper to UI model if different, otherwise use domain model
fun Chat.toPresentation() = ChatsItem(id, name, lastMessage, time, unreadCount, imageUrl)
