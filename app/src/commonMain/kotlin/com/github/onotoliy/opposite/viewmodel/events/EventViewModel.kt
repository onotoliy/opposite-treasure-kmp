package com.github.onotoliy.opposite.viewmodel.events

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class EventViewModel(
    private val repository: IEventRepository,
    private val uuid: String
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Event>>(UiState.Loading)

    val state: StateFlow<UiState<Event>> = _state

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
}
