package com.github.onotoliy.opposite.viewmodel.users

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi

class UserEditModel(
    private val repository: IUserRepository,
    private val uuid: String
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<User>>(UiState.Loading)
    val state: StateFlow<UiState<User>> = _state

    fun load() {
        _state.value = UiState.Loading

        scope.launch {
            try {
                _state.value = UiState.Success(repository.get(uuid))
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class, ExperimentalTime::class)
    fun onSave(event: User, onSuccess: (User) -> Unit) {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val user = withContext(Dispatchers.Default) {
                    repository.update(event)
                }

                withContext(Dispatchers.Main) {
                    onSuccess(user) // переход на другой экран
                }

            } catch (exception: Exception) {
                _state.value = UiState.Error(exception.message ?: "Unknown error")
            }
        }
    }
}
