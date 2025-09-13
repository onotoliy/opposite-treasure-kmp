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
import kotlin.String
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class UserNewViewModel(
    private val repository: IUserRepository
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))

    val state: StateFlow<UiState<Unit>> = _state

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class, ExperimentalTime::class)
    fun onSave(
        login: String,
        name: String,
        birthday: Instant,
        joiningDate: Instant,
        position: String,
        onSuccess: (User) -> Unit
    ) {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val user = withContext(Dispatchers.Default) {
                    repository.create(
                        User(
                            uuid = Uuid.random().toString(),
                            name = name,
                            birthday = birthday,
                            joiningDate = joiningDate,
                            position = position,
                            logo = "",
                            login = login,
                            deposit = ""
                        )
                    )
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
