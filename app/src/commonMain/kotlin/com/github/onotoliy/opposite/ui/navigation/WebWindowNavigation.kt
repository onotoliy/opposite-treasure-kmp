package com.github.onotoliy.opposite.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.github.onotoliy.opposite.di.LocalNavController
import com.github.onotoliy.opposite.ui.cashbox.screens.CashboxScreen
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.events.screens.EventCreateScreen
import com.github.onotoliy.opposite.ui.events.screens.EventEditScreen
import com.github.onotoliy.opposite.ui.events.screens.EventListScreen
import com.github.onotoliy.opposite.ui.events.screens.EventViewScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionCreateScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionEditScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionViewScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionsTableScreen
import com.github.onotoliy.opposite.ui.users.screens.UserCreateScreen
import com.github.onotoliy.opposite.ui.users.screens.UserEditScreen
import com.github.onotoliy.opposite.ui.users.screens.UserListScreen
import com.github.onotoliy.opposite.ui.users.screens.UserViewScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebWindowNavigation(
    onNavHostReady: suspend (NavController) -> Unit = {}
) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        onNavHostReady(navController)
    }

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController, startDestination = "cashbox") {
            composable("cashbox") {
                CashboxScreen(onSelect = { navController.goto(it) })
            }

            composable("events/new") {
                EventCreateScreen(
                    onSelect = { navController.goto(it) }
                )
            }

            composable("events") {
                EventListScreen(
                    onSelect = { navController.goto(it) }
                )
            }

            composable(
                route = "events/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                EventViewScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }

            composable(
                route = "events/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                EventEditScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }

            composable("users") {
                UserListScreen(onSelect = { navController.goto(it) })
            }

            composable(route = "users/new") { backStackEntry ->
                UserCreateScreen(onSelect = { navController.goto(it) })
            }

            composable(
                route = "users/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                UserViewScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }

            composable(
                route = "users/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                UserEditScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }

            composable("transactions") {
                TransactionsTableScreen(onSelect = { navController.goto(it) })
            }

            composable(route = "transactions/new") { backStackEntry ->
                TransactionCreateScreen(onSelect = { navController.goto(it) })
            }

            composable(
                route = "transactions/{uuid}",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                TransactionViewScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }

            composable(
                route = "transactions/{uuid}/edit",
                arguments = listOf(navArgument("uuid") { type = NavType.StringType })
            ) { backStackEntry ->
                TransactionEditScreen(backStackEntry.uuid, onSelect = { navController.goto(it) })
            }
        }
    }



}

private val NavBackStackEntry.uuid: String
    get() = savedStateHandle.get<String>("uuid")
        ?: throw IllegalArgumentException("UUID can not be empty")

private fun NavController.goto(screen: Screen) {
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
