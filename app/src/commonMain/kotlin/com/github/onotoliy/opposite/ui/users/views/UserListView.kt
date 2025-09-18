package com.github.onotoliy.opposite.ui.users.views

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
import com.github.onotoliy.opposite.repositories.name
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.buttons.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.infinity.ListInfinity
import com.github.onotoliy.opposite.ui.components.infinity.TableInfinity
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import io.github.windedge.table.m3.PaginatedDataTable

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
private fun UserMobileItem(user: Deposit, onSelect: (Screen) -> Unit) {
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

                Text(text = user.deposit.toMoneyPrettyString())
            }

            HorizontalDivider()
        }
    }

}

@Composable
fun UserTableWebView(viewModel: UserListModel, onSelect: (Screen) -> Unit) {
    val state by viewModel.loadState.collectAsState()
    val values by viewModel.values.collectAsState()
    val hasLoadMore by viewModel.hasLoadMore.collectAsState()

    TableInfinity(
        loadingState = state,
        values = values,
        canLoadMore = hasLoadMore,
        onLoadMore = viewModel::load,
        header = {
            Text("ФИО")
            Text("Депозит")
        }
    ) { user ->
        Text(
            modifier = Modifier.clickable {
                onSelect(Screen.UserViewScreen(user.uuid))
            },
            text = user.name
        )
        Text(user.deposit)
    }
}
