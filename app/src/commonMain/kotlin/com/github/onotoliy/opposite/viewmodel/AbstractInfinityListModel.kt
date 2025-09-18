package com.github.onotoliy.opposite.viewmodel

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.repositories.HttpException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class AbstractInfinityListModel<T>: ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _loadState = MutableStateFlow<UiState>(UiState.Success)
    private val _values = MutableStateFlow<List<T>>(mutableListOf())
    private val _hasLoadMore = MutableStateFlow<Boolean>(true)

    val loadState: StateFlow<UiState> = _loadState
    val values: StateFlow<List<T>> = _values
    val hasLoadMore: StateFlow<Boolean> = _hasLoadMore

    fun load(reload: Boolean = true) {
        if (_loadState.value is UiState.Loading) {
            return
        }

        if (reload) {
            _values.value = mutableListOf<T>()
            _hasLoadMore.value = true
        }

        if (!_hasLoadMore.value) {
            return
        }

        _loadState.value = UiState.Loading

        scope.launch {
            try {
                val page = getAll(_values.value.size, 10)

                _hasLoadMore.value = page.size == 10
                _values.value = _values.value + page
                _loadState.value = UiState.Success
            } catch (e: HttpException) {
                _loadState.value = UiState.Error(e.message)
            }
        }
    }

    protected suspend abstract fun getAll(offset: Int = 0, numberOfRows: Int = 10): List<T>
}
