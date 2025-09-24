package com.github.onotoliy.opposite.ui.transactions.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.transactions.views.TransactionInformationView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.models.TransactionViewModel
import com.github.onotoliy.opposite.viewmodel.UiState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionViewScreen(
    uuid: String,
    viewModel: TransactionViewModel = koinViewModel { parametersOf(uuid) }
) {
    val state by viewModel.loadState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Фотографии")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.Photo)

    LaunchedEffect(Unit) {
        viewModel.load()
    }

    LaunchedEffect(state) {
        if (state is UiState.Error) {
            scaffoldState.snackbarHostState.showSnackbar((state as UiState.Error).message)
        }
    }

    ApplicationScaffold {
        Column {
            when (state) {
                is UiState.Error -> {}
                UiState.Loading -> LinearProgressIndicator(Modifier.fillMaxWidth())
                is UiState.Success -> {}
            }

            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                edgePadding = 0.dp
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Icon(imageVector = icons[index], contentDescription = title)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = title)
                        }
                    }
                }
            }

            when (selectedTabIndex) {
                0 -> TransactionInformationView(viewModel)
                1 -> FilesTab(viewModel.receipts.value)
            }
        }
    }
}


@Composable
private fun FilesTab(files: List<DrawableResource>) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 120.dp), // авто-сетка
    ) {
        items(files.size) { idx ->
            Image(
                painter = painterResource(files[idx]),
                contentDescription = null,
                alignment = Alignment.TopCenter,
                modifier = Modifier.size(120.dp)
            )
        }
    }
}
