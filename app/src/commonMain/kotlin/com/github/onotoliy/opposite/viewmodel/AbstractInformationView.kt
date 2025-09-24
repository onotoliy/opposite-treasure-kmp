package com.github.onotoliy.opposite.viewmodel

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.onotoliy.opposite.repositories.NUMBER_OF_ROWS
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import io.github.windedge.table.PaginationState
import io.github.windedge.table.rememberPaginationState

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> AbstractTableView(
    viewModel: AbstractInfinityTableModel<T>,
    content: @Composable ColumnScope.() -> Unit
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

        content()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> AbstractCreateView(
    viewModel: AbstractCreateModel<T>,
    content: @Composable ColumnScope.() -> Unit
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

        content()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> AbstractEditView(
    viewModel: AbstractEditModel<T>,
    content: @Composable ColumnScope.() -> Unit
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

        content()
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <T> AbstractInformationView(
    viewModel: AbstractViewModel<T>,
    content: @Composable ColumnScope.() -> Unit
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    Column {
        when (state) {
            is UiState.Error -> {}
            UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
            is UiState.Success -> {}
        }

        content()
    }
}