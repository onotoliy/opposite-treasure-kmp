package com.github.onotoliy.opposite.viewmodel.events

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.EventCacheRepository
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EventsListModel(
    private val repository: IEventRepository
): ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Loading)
    private val _values = MutableStateFlow<List<Event>>(mutableListOf())
    private val _hasLoadMore = MutableStateFlow<Boolean>(true)

    val state: StateFlow<UiState<Unit>> = _state
    val values: StateFlow<List<Event>> = _values
    val hasLoadMore: StateFlow<Boolean> = _hasLoadMore

    private val items = mutableListOf<Event>()

    fun load() {
        if (!_hasLoadMore.value) return

        _state.value = UiState.Loading

        scope.launch {
            try {
                val page = repository.getAll(null, _values.value.size, 10)

                _hasLoadMore.value = page.size == 10
                _values.value = _values.value + page
                _state.value = UiState.Success(Unit)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
