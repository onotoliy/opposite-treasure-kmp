package com.github.onotoliy.opposite.viewmodel.events

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.String
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class EventNewViewModel(
    private val repository: IEventRepository
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Unit>>(UiState.Success(Unit))

    val state: StateFlow<UiState<Unit>> = _state

    @OptIn(ExperimentalUuidApi::class, ExperimentalTime::class, ExperimentalTime::class)
    fun onSave(name: String, contribution: String, deadline: Instant, onSuccess: (Event) -> Unit) {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val event = withContext(Dispatchers.Default) {
                    repository.create(Event(
                        uuid = Uuid.random().toString(),
                        name = name,
                        contribution = contribution,
                        total = contribution,
                        deadline = deadline.toString(),
                        creationDate = Clock.System.now().toString(),
                        author = Option("", ""),
                        deletionDate = null
                    ))
                }

                withContext(Dispatchers.Main) {
                    onSuccess(event) // переход на другой экран
                }

            } catch (exception: Exception) {
                _state.value = UiState.Error(exception.message ?: "Unknown error")
            }
        }
    }
}
