package com.github.onotoliy.opposite.viewmodel

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.repositories.HttpException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class, ExperimentalTime::class)
abstract class AbstractCreateModel<T>: ViewModel() {
    protected val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    protected val _loadState = MutableStateFlow<UiState>(UiState.Success)

    val loadState: StateFlow<UiState> = _loadState

    fun init() {
        _loadState.value = UiState.Success
    }

    fun onSave(value: T, onSuccess: (T) -> Unit) {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                val newValue = withContext(Dispatchers.Default) {
                    create(value)
                }

                withContext(Dispatchers.Main) {
                    onSuccess(newValue) // переход на другой экран
                }

            } catch (exception: HttpException) {
                _loadState.value = UiState.Error(exception.message)
            }
        }
    }

    protected abstract suspend fun create(value: T): T
}
