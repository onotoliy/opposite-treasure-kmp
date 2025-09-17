package com.github.onotoliy.opposite.ui.transactions.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.repositories.name
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.repositories.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionCreateModel(
    private val repository: TransactionRepository,
    private val erepository: EventRepository,
    private val drepository: DepositeRepository
) : AbstractCreateModel<Transaction>() {

    private val _deposits = MutableStateFlow<List<Option>>(mutableListOf())
    private val _events = MutableStateFlow<List<Option>>(mutableListOf())

    val events: StateFlow<List<Option>> = _events
    val deposits: StateFlow<List<Option>> = _deposits

    fun getEvents(q: String?, type: Transaction.Type?) {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                _events.value = erepository.getAll(q, 0, 10).map { Option(it.uuid, it.name) }
                _loadState.value = UiState.Success
            } catch (e: Exception) {
                _loadState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getDeposits(q: String?, type: Transaction.Type?) {
        _loadState.value = UiState.Loading

        scope.launch {
            try {
                _deposits.value = drepository.getAll(q, 0, 10).map { Option(it.uuid, it.name) }
                _loadState.value = UiState.Success
            } catch (e: Exception) {
                _loadState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun create(value: Transaction): Transaction = repository.create(value)

}