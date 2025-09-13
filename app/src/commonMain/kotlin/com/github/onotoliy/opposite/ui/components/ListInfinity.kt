package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.viewmodel.UiState

@Composable
fun <T> ListInfinity(
    loadingState: UiState<Unit>,
    values: List<T>,
    canLoadMore: Boolean,
    onLoadMore: () -> Unit,
    content: @Composable (T) -> Unit
) {
    val listState = rememberLazyListState()

    LaunchedEffect(Unit) {
        onLoadMore()
    }

    LaunchedEffect(listState, values, canLoadMore, loadingState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                val lvx = lastVisibleIndex ?: return@collect

                if (loadingState is UiState.Success && lvx >= values.lastIndex - 3) {
                        onLoadMore()
                }
            }
    }

    Column {
        when (loadingState) {
            is UiState.Error -> {
                Text("Ошибка: ${loadingState.message}")
            }
            is UiState.Loading -> {
                LinearProgressIndicator()
            }
            is UiState.Success<Unit> -> {

            }
        }

        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(values) { index, value ->
                content(value)
            }
        }
    }
}
