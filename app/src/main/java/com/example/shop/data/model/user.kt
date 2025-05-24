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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.shop.R

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




@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.user.collectAsState()

    var name by remember { mutableStateOf(TextFieldValue(user.name)) }
    var email by remember { mutableStateOf(TextFieldValue(user.email)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val avatarPainter = if (user.avatarUrl != null) {
            rememberAsyncImagePainter(user.avatarUrl)
        } else {
            painterResource(id = R.drawable.user_outline)
        }

        Image(
            painter = avatarPainter,
            contentDescription = "User Avatar",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = {
            viewModel.updateUser(user.copy(name = name.text, email = email.text))
        }) {
            Text("Save")
        }
    }
}
