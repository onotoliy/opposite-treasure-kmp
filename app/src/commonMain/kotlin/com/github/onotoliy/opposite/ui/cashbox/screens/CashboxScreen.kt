package com.github.onotoliy.opposite.ui.cashbox.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.ui.cashbox.models.CashboxViewModel
import com.github.onotoliy.opposite.ui.components.ErrorMessage
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.screens.UserViewScreen
import com.github.onotoliy.opposite.viewmodel.UiState
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.ExperimentalTime

@OptIn( ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun CashboxScreen(viewModel: CashboxViewModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    when (state) {
        is UiState.Error -> ErrorMessage((state as UiState.Error).message)
        UiState.Loading -> LinearProgressIndicator()
        is UiState.Success -> UserViewScreen(data.uuid, onSelect = onSelect)
    }
}