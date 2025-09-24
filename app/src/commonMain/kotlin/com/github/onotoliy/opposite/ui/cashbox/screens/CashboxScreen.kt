package com.github.onotoliy.opposite.ui.cashbox.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.cashbox.models.CashboxViewModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.screens.UserViewScreen
import com.github.onotoliy.opposite.viewmodel.UiState
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.ExperimentalTime

@OptIn( ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun CashboxScreen(viewModel: CashboxViewModel = koinViewModel()) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val data by viewModel.info.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    when (state) {
        is UiState.Error -> { }
        UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
        is UiState.Success -> UserViewScreen(data.uuid)
    }
}
