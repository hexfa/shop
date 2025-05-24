package com.example.shop.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class User(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String? = null
)

interface UserRepository {
    fun getUser(): Flow<User>
    suspend fun updateUser(user: User)
}

class InMemoryUserRepository : UserRepository {

    private val _userFlow = MutableStateFlow(
        User(id = "1", name = "John Doe", email = "john@example.com")
    )

    override fun getUser(): Flow<User> = _userFlow.asStateFlow()

    override suspend fun updateUser(user: User) {
        _userFlow.value = user
    }
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    val user: StateFlow<User> = repository.getUser()
        .stateIn(viewModelScope, SharingStarted.Lazily, User("", "", ""))

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }
}