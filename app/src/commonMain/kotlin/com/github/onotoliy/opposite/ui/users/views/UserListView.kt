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
import com.github.onotoliy.opposite.repositories.NUMBER_OF_ROWS
import com.github.onotoliy.opposite.repositories.name
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.components.buttons.AddButton
import com.github.onotoliy.opposite.ui.components.buttons.AddFloatingActionButton
import com.github.onotoliy.opposite.ui.components.infinity.ListInfinity
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.components.scaffold.goto
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.Screen.UserViewScreen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.models.UserListAdapter
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import com.github.onotoliy.opposite.ui.users.models.UserTableModel
import com.github.onotoliy.opposite.viewmodel.AbstractTableView
import io.github.windedge.table.m3.PaginatedDataTable
import io.github.windedge.table.rememberPaginationState

@Composable
expect fun UserListView(listAdapter: UserListAdapter, hasActionButtons: Boolean)

@Composable
fun UserListMobileView(viewModel: UserListModel, hasActionButtons: Boolean) {
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.floatingActionButton = {
        AddFloatingActionButton { navHostController.goto(Screen.UserNewScreen) }
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
        Row(
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .clickable { navHostController.goto(UserViewScreen(user.uuid)) },
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
}

@Composable
fun UserTableWebView(viewModel: UserTableModel, hasActionButtons: Boolean) {
    val navHostController = LocalNavHostController.current
    val total by viewModel.total.collectAsState()
    val page = rememberPaginationState(total, pageSize = NUMBER_OF_ROWS)

    AbstractTableView(viewModel) {
        if (hasActionButtons) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AddButton {
                    navHostController.goto(Screen.UserNewScreen)
                }
            }
        }

        PaginatedDataTable(
            columns = {
                column { Text("ФИО") }
                column { Text("Депозит") }
            },
            paginationState = page,
            onPageChanged = {
                viewModel.sload((it.pageIndex - 1) * NUMBER_OF_ROWS)
            }
        ) { deposit: Deposit ->
            row(modifier = Modifier) {
                cell {
                    Text(
                        modifier = Modifier.goto(Screen.UserViewScreen(deposit.uuid)),
                        text = deposit.name
                    )
                }
                cell { Text(deposit.deposit.toMoneyPrettyString()) }
            }
        }
    }
}
