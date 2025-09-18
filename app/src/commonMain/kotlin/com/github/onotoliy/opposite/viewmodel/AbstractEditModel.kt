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
abstract class AbstractEditModel<T>(private val uuid: String): ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _loadState = MutableStateFlow<UiState>(UiState.Loading)
    private val _info= MutableStateFlow<T>(defaultValue)

    val info: StateFlow<T> = _info
    val loadState: StateFlow<UiState> = _loadState

    fun load() {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                _info.value = get(uuid)
                _loadState.value = UiState.Success
            } catch (e: HttpException) {
                _loadState.value = UiState.Error(e.message)
            }
        }
    }

    fun onSave(value: T, onSuccess: (T) -> Unit) {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                val newValue = withContext(Dispatchers.Default) {
                    update(value)
                }

                withContext(Dispatchers.Main) {
                    onSuccess(newValue)
                }

            } catch (exception: HttpException) {
                _loadState.value = UiState.Error(exception.message)
            }
        }
    }

    protected abstract suspend fun get(uuid: String): T
    protected abstract suspend fun update(value: T): T

    protected abstract val defaultValue: T
}
