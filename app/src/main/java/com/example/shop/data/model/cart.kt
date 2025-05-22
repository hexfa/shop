package com.example.shop.data.model

import kotlinx.coroutines.flow.Flow

data class CartItem(
    val id: String,
    val name: String,
    val price: Double,
    val quantity: Int
)

interface CartRepository {
    fun getCartItems(): Flow<List<CartItem>>
    suspend fun removeItem(itemId: String)
    suspend fun clearCart()
}
