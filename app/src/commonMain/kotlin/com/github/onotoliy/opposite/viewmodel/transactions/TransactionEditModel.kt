package com.github.onotoliy.opposite.viewmodel.transactions

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionEditModel(
    private val repository: ITransactionRepository,
    private val uuid: String
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<Transaction>>(UiState.Loading)

    val state: StateFlow<UiState<Transaction>> = _state

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

    fun onSave(transaction: Transaction, onSuccess: (Transaction) -> Unit) {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val event = withContext(Dispatchers.Default) {
                    repository.update(transaction)
                }

                withContext(Dispatchers.Main) {
                    onSuccess(event)
                }

            } catch (exception: Exception) {
                _state.value = UiState.Error(exception.message ?: "Unknown error")
            }
        }
    }
}