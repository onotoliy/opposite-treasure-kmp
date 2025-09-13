package com.github.onotoliy.opposite.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class AbstractViewModel<T>() : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _loadState = MutableStateFlow<UiState>(UiState.Loading)

    private val _info= MutableStateFlow<T>(defaultValue)

    val info: StateFlow<T> = _info

    val loadState: StateFlow<UiState> = _loadState

    fun load() {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                _info.value = get()
                _loadState.value = UiState.Success
            } catch (e: Exception) {
                _loadState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    protected abstract suspend fun get(): T

    protected abstract suspend fun loadAdditionalValues()

    protected abstract val defaultValue: T
}