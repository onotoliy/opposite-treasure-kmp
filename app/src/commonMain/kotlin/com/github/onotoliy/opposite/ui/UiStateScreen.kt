package com.github.onotoliy.opposite.ui

import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.onotoliy.opposite.viewmodel.UiState

@Composable
fun UiStateScreen(state: UiState, load: () -> Unit, content: @Composable () -> Unit) {
    LaunchedEffect(Unit) {
        load()
    }

    when(state) {
        is UiState.Error -> ErrorMessage(state.message)
        UiState.Loading -> LoadingSpinner()
        is UiState.Success -> content()
    }
}