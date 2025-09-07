package com.github.onotoliy.opposite.viewmodel.transactions

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TransactionsListModel(
    private val repository: ITransactionRepository
): ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<List<Transaction>>>(UiState.Loading)

    val state: StateFlow<UiState<List<Transaction>>> = _state

    fun load() {
        _state.value = UiState.Loading

        scope.launch {
            try {
                _state.value = UiState.Success(repository.getAll())
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}