package com.example.shop.data.model

import kotlinx.coroutines.flow.Flow

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
