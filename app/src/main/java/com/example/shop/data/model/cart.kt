package com.example.shop.data.model

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
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

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(),
    onCheckout: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Your Cart") })
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding).fillMaxSize()) {
                if (uiState.items.isEmpty()) {
                    Text("Your cart is empty", modifier = Modifier.padding(16.dp))
                } else {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(uiState.items) { item ->
                           // CartItemRow(item = item, onRemove = { viewModel.removeItem(it) })
                        }
                    }
                    Divider()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total:")
                        Text("$${"%.2f".format(uiState.totalPrice)}")
                    }
                    Button(
                        onClick = onCheckout,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("Checkout")
                    }
                }
            }
        }
    )
}

