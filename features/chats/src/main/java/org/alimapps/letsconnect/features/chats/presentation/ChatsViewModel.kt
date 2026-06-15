package org.alimapps.letsconnect.features.chats.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

data class ChatsItem(
    val id: String,
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0,
    val imageUrl: String? = null
)

@HiltViewModel
class ChatsViewModel @Inject constructor() : ViewModel() {

    private val _allChats = MutableStateFlow<List<ChatsItem>>(emptyList())
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    val chats: StateFlow<List<ChatsItem>> = combine(_allChats, _searchQuery) { chats, query ->
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
        loadChats()
    }

    private fun loadChats() {
        _allChats.value = listOf(
            ChatsItem("1", "John Doe", "Hey, how are you?", "10:30 AM", 2, "https://i.pravatar.cc/150?u=1"),
            ChatsItem("2", "Jane Smith", "Meeting at 2 PM", "Yesterday", 0, null),
            ChatsItem("3", "Family Group", "Mom: Happy Birthday!", "Monday", 5, "https://i.pravatar.cc/150?u=3"),
            ChatsItem("4", "Work Buddies", "Bob sent a photo", "12/05/2024", 0, "https://i.pravatar.cc/150?u=4")
        )
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
    }
}
