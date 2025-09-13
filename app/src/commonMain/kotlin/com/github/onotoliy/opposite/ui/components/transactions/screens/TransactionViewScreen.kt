package com.github.onotoliy.opposite.ui.components.transactions.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Photo
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.components.transactions.TransactionInformationView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionView
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionViewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TransactionViewScreen(
    uuid: String,
    model: TransactionViewModel = koinViewModel { parametersOf(uuid) },
    onSelect: (Screen) -> Unit
) {
    val state by model.state.collectAsState()

    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Информация", "Фотографии")
    val icons = listOf(Icons.Filled.Info, Icons.Outlined.Photo)

    UiStateScreen<TransactionView>(state, load = model::load) { data ->
        ApplicationScaffold(
            onSelect = onSelect
        ) {
            Column {
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
                    0 -> TransactionInformationView(data.transactions, onSelect)
                    1 -> FilesTab(data.files)
                }
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