package com.github.onotoliy.opposite.viewmodel

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.repositories.HttpException
import com.github.onotoliy.opposite.repositories.NUMBER_OF_ROWS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class Page<T>(val total: Int, val values: List<T>)

abstract class AbstractInfinityTableModel<T>: ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _loadState = MutableStateFlow<UiState>(UiState.Success)
    private val _values = MutableStateFlow<List<T>>(mutableListOf())
    private val _total = MutableStateFlow<Int>(0)

    val loadState: StateFlow<UiState> = _loadState
    val values: StateFlow<List<T>> = _values
    val total: StateFlow<Int> = _total

    protected suspend abstract fun getAll(offset: Int, numberOfRows: Int): Page<T>

    fun load(offset: Int) {
        println("offset " + offset)

        if (_loadState.value is UiState.Loading) {
            return
        }

        _loadState.value = UiState.Loading

        scope.launch {
            try {
                val page = getAll(offset, NUMBER_OF_ROWS)

                _total.value = page.total
                _values.value = page.values
                _loadState.value = UiState.Success
            } catch (e: HttpException) {
                _loadState.value = UiState.Error(e.message)
            }
        }
    }
}
