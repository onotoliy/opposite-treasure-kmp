package com.github.onotoliy.opposite.viewmodel

sealed class UiState {
    object Loading : UiState()
    object Success : UiState()
    data class Error(val message: String) : UiState()
}
