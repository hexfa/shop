package com.example.shop.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

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


data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val totalPrice: Double = 0.0
)

class CartViewModel(
    private val repository: CartRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> = _uiState.asStateFlow()

    init {
        repository.getCartItems()
            .onEach { items ->
                val total = items.sumOf { it.price * it.quantity }
                _uiState.value = CartUiState(items, total)
            }
            .launchIn(viewModelScope)
    }

    fun removeItem(itemId: String) {
        viewModelScope.launch {
            repository.removeItem(itemId)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

}

