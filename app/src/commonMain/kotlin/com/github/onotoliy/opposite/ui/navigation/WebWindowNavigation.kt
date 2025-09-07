package com.github.onotoliy.opposite.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.onotoliy.opposite.repositories.events
import com.github.onotoliy.opposite.ui.WebWindowScaffold
import com.github.onotoliy.opposite.ui.cashbox.CashboxScreen
import com.github.onotoliy.opposite.ui.events.EventEditScreen
import com.github.onotoliy.opposite.ui.events.EventViewScreen
import com.github.onotoliy.opposite.ui.events.EventsTableScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionEditScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionViewScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionsTableScreen
import com.github.onotoliy.opposite.ui.users.UserEditScreen
import com.github.onotoliy.opposite.ui.users.UserViewScreen
import com.github.onotoliy.opposite.ui.users.UsersTableScreen

@Composable
fun WebWindowNavigation(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()

    WebWindowScaffold(
        onSelect = { navController.navigate1(it) }
    ) {
        NavHost(navController, startDestination = "cashbox") {
            composable("cashbox") {
                CashboxScreen()
            }

            composable("events") {
                EventsTableScreen(
                    onSelect = { navController.navigate1(it) }
                )
            }

            composable(
                route = "event/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                EventViewScreen(uuid)
            }

            composable(
                route = "event/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                EventEditScreen(uuid)
            }

            composable("users") {
                UsersTableScreen()
            }

            composable(
                route = "users/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                UserViewScreen()
            }

            composable(
                route = "users/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                UserEditScreen()
            }

            composable("transactions") {
                TransactionsTableScreen()
            }

            composable(
                route = "transaction/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                TransactionViewScreen()
            }

            composable(
                route = "transaction/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                TransactionEditScreen()
            }
        }

        LaunchedEffect(navController) {
            onNavHostReady(navController)
        }
    }
}

fun NavController.navigate1(screen: Screen) {
    val route = when (screen) {
        is Screen.CashScreen -> "cashbox"

        is Screen.EventsScreen -> "events"
        is Screen.EventEditScreen -> "event/${screen.uuid}/edit"
        is Screen.EventViewScreen -> "event/${screen.uuid}"

        is Screen.TransactionsScreen -> "transactions"
        is Screen.TransactionEditScreen -> "transaction/${screen.uuid}/edit"
        is Screen.TransactionViewScreen -> "transaction/${screen.uuid}"

        is Screen.UsersScreen -> "users"
        is Screen.UserEditScreen -> "user/${screen.uuid}/edit"
        is Screen.UserViewScreen -> "user/${screen.uuid}"
    }
    this.navigate(route)
}
