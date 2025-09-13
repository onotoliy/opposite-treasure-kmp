package com.github.onotoliy.opposite.viewmodel.transactions

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class TransactionNewViewModel(
    private val repository: ITransactionRepository
) : ViewModel() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Transaction>>(UiState.Loading)

    val state: StateFlow<UiState<Transaction>> = _state

    @OptIn(ExperimentalTime::class, ExperimentalUuidApi::class)
    fun onSave(
        name: String,
        cash: String,
        type: TransactionType,
        person: Option?,
        event: Option?,
        transactionDate: Instant,
        onSuccess: (Transaction) -> Unit
    ) {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val event = withContext(Dispatchers.Default) {
                    repository.create(
                        Transaction(
                            uuid = Uuid.random().toString(),
                            name = name,
                            cash = cash,
                            type = type,
                            person = person,
                            event = event,
                            transactionDate = transactionDate,
                            creationDate = Clock.System.now(),
                            author = Option("", ""),
                            deletionDate = null
                        )
                    )
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