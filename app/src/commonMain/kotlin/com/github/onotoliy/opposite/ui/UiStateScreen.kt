package com.github.onotoliy.opposite.ui

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.onotoliy.opposite.viewmodel.UiState

@Composable
fun <T> UiStateScreen(state: UiState<T>, load: () -> Unit, content: @Composable (T) -> Unit) {
    LaunchedEffect(Unit) {
        load()
    }

    when(state) {
        is UiState.Error -> ErrorMessage(state.message)
        UiState.Loading -> LoadingSpinner()
        is UiState.Success<T> -> content(state.data)
    }
}