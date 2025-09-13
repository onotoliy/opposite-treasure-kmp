package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.components.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.ListInfinity
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.users.UserListModel

@Composable
expect fun UserListView(viewModel: UserListModel, onSelect: (Screen) -> Unit)

@Composable
fun UserListMobileView(viewModel: UserListModel, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { onSelect(Screen.UserNewScreen) }
    }
    val state by viewModel.loadState.collectAsState()
    val values by viewModel.values.collectAsState()
    val hasLoadMore by viewModel.hasLoadMore.collectAsState()

    ListInfinity(
        loadingState = state,
        values = values,
        canLoadMore = hasLoadMore,
        onLoadMore = viewModel::load
    ) { user ->
        UserMobileItem(user, onSelect)
    }
}

@Composable
private fun UserMobileItem(user: User, onSelect: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable { onSelect(Screen.UserViewScreen(user.uuid)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Outlined.People, contentDescription = null)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = user.name)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = user.deposit)
            }

            HorizontalDivider()
        }
    }

}

@Composable
fun UserTableWebView(viewModel: UserListModel, onSelect: (Screen) -> Unit) {
//    DataTable(
//        columns = {
//            headerBackground {
//                Box(modifier = Modifier.background(color = Color.LightGray))
//            }
//            column { Text("ФИО") }
//            column { Text("Депозит") }
//        }
//    ) {
//        users.forEach { record ->
//            row(modifier = Modifier) {
//                cell {
//                    Text(
//                        modifier = Modifier.clickable {
//                            onSelect(Screen.UserViewScreen(record.uuid))
//                        },
//                        text = record.name
//                    )
//                }
//                cell { Text(record.deposit) }
//            }
//        }
//    }
}
