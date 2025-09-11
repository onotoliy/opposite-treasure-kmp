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
    private val _state = MutableStateFlow<UiState<List<Event>>>(UiState.Loading)

    val state: StateFlow<UiState<List<Event>>> = _state

    fun load(): List<Event> {
        _state.value = UiState.Loading

        scope.launch {
            try {
                _state.value = UiState.Success(repository.getAll(null, 0, 10))
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }

        return EventCacheRepository.EVENTS
    }

    fun loadMore() {
//        if (isLoadingMore || endReached) return
//
//        isLoadingMore = true
//
//        // emit current state with same list but could add a "loading" UI if needed
//        // We'll keep using UiState.Success with current items
//        scope.launch {
//            try {
//                val page = withContext(Dispatchers.Default) {
//                    repository.getAll(null, currentOffset, pageSize)
//                } ?: emptyList()
//
//                // append
//                items.addAll(page)
//                currentOffset = items.size
//                endReached = page.size < pageSize
//
//                _state.value = UiState.Success(items.toList())
//            } catch (e: Exception) {
//                // keep existing items but show error
//                _state.value = UiState.Error(e.message ?: "Unknown error")
//            } finally {
//                isLoadingMore = false
//            }
//        }
    }
}
