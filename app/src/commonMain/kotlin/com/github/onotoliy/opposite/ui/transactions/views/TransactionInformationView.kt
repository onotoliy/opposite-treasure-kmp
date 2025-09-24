package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.buttons.DeleteButton
import com.github.onotoliy.opposite.ui.components.buttons.DeleteIconButton
import com.github.onotoliy.opposite.ui.components.buttons.EditButton
import com.github.onotoliy.opposite.ui.components.buttons.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.transactions.TransactionInformationLayout
import com.github.onotoliy.opposite.ui.transactions.models.TransactionViewModel
import com.github.onotoliy.opposite.viewmodel.AbstractInformationView
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionInformationView(viewModel: TransactionViewModel)

@Composable
fun TransactionInformationMobileView(viewModel: TransactionViewModel) {
    val transaction by viewModel.info.collectAsState()
    val navHostController = LocalNavHostController.current

    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            navHostController.goto(
                Screen.TransactionEditScreen(transaction.uuid)
            )
        }
    }
    LocalMobileScafoldState.current.actionsTopBar = {
        DeleteIconButton {
            viewModel.onDelete(transaction.uuid) {
                navHostController.goto(Screen.TransactionListScreen)
            }
        }
    }

    AbstractInformationView(viewModel = viewModel) {
        TransactionInformationLayout(transaction, navHostController::goto)
    }
}
@OptIn(ExperimentalTime::class)
@Composable
fun TransactionInformationWebView(viewModel: TransactionViewModel) {
    val transaction by viewModel.info.collectAsState()
    val navHostController = LocalNavHostController.current

    AbstractInformationView(viewModel = viewModel) {
        TransactionInformationLayout(transaction, navHostController::goto)

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp),) {
            EditButton {
                navHostController.goto(Screen.TransactionEditScreen(transaction.uuid))
            }

            DeleteButton {
                viewModel.onDelete(transaction.uuid) {
                    navHostController.goto(Screen.TransactionListScreen)
                }
            }
        }
    }
}
