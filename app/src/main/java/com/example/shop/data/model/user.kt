package com.example.shop.data.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

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