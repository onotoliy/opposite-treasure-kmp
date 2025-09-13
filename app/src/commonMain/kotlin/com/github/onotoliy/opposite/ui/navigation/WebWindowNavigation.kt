package com.github.onotoliy.opposite.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.onotoliy.opposite.ui.cashbox.CashboxScreen
import com.github.onotoliy.opposite.ui.components.events.screens.EventEditScreen
import com.github.onotoliy.opposite.ui.components.events.screens.EventCreateScreen
import com.github.onotoliy.opposite.ui.components.events.screens.EventListScreen
import com.github.onotoliy.opposite.ui.components.events.screens.EventViewScreen
import com.github.onotoliy.opposite.ui.components.users.screens.UserEditScreen
import com.github.onotoliy.opposite.ui.components.users.screens.UserCreateScreen
import com.github.onotoliy.opposite.ui.components.transactions.screens.TransactionCreateScreen
import com.github.onotoliy.opposite.ui.components.transactions.screens.TransactionEditScreen
import com.github.onotoliy.opposite.ui.components.transactions.screens.TransactionViewScreen
import com.github.onotoliy.opposite.ui.components.transactions.screens.TransactionsTableScreen
import com.github.onotoliy.opposite.ui.users.UserViewScreen
import com.github.onotoliy.opposite.ui.users.UserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebWindowNavigation(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()


        NavHost(navController, startDestination = "cashbox") {
            composable("cashbox") {
                CashboxScreen(onSelect = { navController.navigate1(it) })
            }

            composable("events/new") {
                EventCreateScreen(
                    onSelect = { navController.navigate1(it) }
                )
            }

            composable("events") {
                EventListScreen(
                    onSelect = { navController.navigate1(it) }
                )
            }

            composable(
                route = "events/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                EventViewScreen(uuid, onSelect = { navController.navigate1(it) })
            }

            composable(
                route = "events/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                EventEditScreen(uuid, onSelect = { navController.navigate1(it) })
            }

            composable("users") {
                UserListScreen(onSelect = { navController.navigate1(it) })
            }

            composable(route = "users/new") { backStackEntry ->

                UserCreateScreen(onSelect = { navController.navigate1(it) })
            }

            composable(
                route = "users/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                UserViewScreen(uuid, onSelect = { navController.navigate1(it) })
            }

            composable(
                route = "users/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                UserEditScreen(uuid, onSelect = { navController.navigate1(it) })
            }

            composable("transactions") {
                TransactionsTableScreen(onSelect = { navController.navigate1(it) })
            }

            composable(route = "transactions/new",) { backStackEntry ->
                TransactionCreateScreen(onSelect = { navController.navigate1(it) })
            }

            composable(
                route = "transactions/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                TransactionViewScreen(uuid, onSelect = { navController.navigate1(it) })
            }

            composable(
                route = "transactions/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                val uuid = backStackEntry.savedStateHandle.get<String>("uuid")
                    ?: throw IllegalArgumentException()

                TransactionEditScreen(uuid, onSelect = { navController.navigate1(it) })
            }
        }

        LaunchedEffect(navController) {
            onNavHostReady(navController)
        }

}

fun NavController.navigate1(screen: Screen) {
    val route = when (screen) {
        is Screen.CashScreen -> "cashbox"

        is Screen.EventsScreen -> "events"
        is Screen.EventEditScreen -> "events/${screen.uuid}/edit"
        is Screen.EventViewScreen -> "events/${screen.uuid}"
        is Screen.EventNewScreen -> "events/new"

        is Screen.TransactionsScreen -> "transactions"
        is Screen.TransactionEditScreen -> "transactions/${screen.uuid}/edit"
        is Screen.TransactionViewScreen -> "transactions/${screen.uuid}"
        is Screen.TransactionNewScreen -> "transactions/new"

        is Screen.UsersScreen -> "users"
        is Screen.UserEditScreen -> "users/${screen.uuid}/edit"
        is Screen.UserViewScreen -> "users/${screen.uuid}"
        is Screen.UserNewScreen -> "users/new"
    }
    this.navigate(route)
}
