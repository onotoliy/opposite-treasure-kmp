package com.github.onotoliy.opposite.ui.components.infinity

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.events.models.EventListModel

@Composable
fun <T> TableInfinity(
    viewModel: EventListModel,
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
    onLoadMore: (offset: Int) -> List<T>,
    values: List<T>,
    onSuccess: (newValues: List<T>, hasMoreItems: Boolean) -> Unit,
    onError: (String) -> Unit
) {
    try {
        val page = onLoadMore(values.size)
        val newValues = values + page
        val total = page.size
        onSuccess(newValues, newValues.size < total)
    } catch (e: Exception) {
        onError("Ошибка загрузки: ${e.message}")
    }
}