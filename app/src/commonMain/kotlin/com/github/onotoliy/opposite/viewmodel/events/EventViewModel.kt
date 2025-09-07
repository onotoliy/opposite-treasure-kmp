package com.github.onotoliy.opposite.viewmodel.events

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

data class EventView(
    val event: Event,
    val logo: DrawableResource
)

open class EventViewModel(
    private val events: IEventRepository,
    private val files: IFileRepository,
    private val uuid: String
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<EventView>>(UiState.Loading)
    val state: StateFlow<UiState<EventView>> = _state

    fun load() {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val view = EventView(
                    event = events.get(uuid),
                    logo = files.download(uuid)
                )

                _state.value = UiState.Success(view)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
