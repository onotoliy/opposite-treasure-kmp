package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Page
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel

@Composable
fun <T> TableInfinity(
    viewModel: EventsListModel,
    content: @Composable (List<T>) -> Unit
) {

//    Column {
//        when (state) {
//            is UiState.Error -> {
//
//            }
//
//            UiState.Loading -> {
//                LinearProgressIndicator()
//            }
//
//            is UiState.Success<*> -> {
//
//            }
//        }
//
//
////        content(values)
//
//        if (state is UiState.Success && canLoadMore) {
//            Box(
//                modifier = Modifier.fillMaxWidth().padding(12.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Button(onClick = {
//                    onLoadMore()
//                }) {
//                    Text("Загрузить ещё")
//                }
//            }
//        }
//    }
}

private fun <T> load(
    onLoadMore: (offset: Int) -> Page<T>,
    values: List<T>,
    onSuccess: (newValues: List<T>, hasMoreItems: Boolean) -> Unit,
    onError: (String) -> Unit
) {
    try {
        val page = onLoadMore(values.size)
        val newValues = values + page.context
        val total = page.meta.total
        onSuccess(newValues, newValues.size < total)
    } catch (e: Exception) {
        onError("Ошибка загрузки: ${e.message}")
    }
}