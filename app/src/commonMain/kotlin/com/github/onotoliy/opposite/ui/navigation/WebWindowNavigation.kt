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
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold
import com.github.onotoliy.opposite.ui.events.EventEditScreen
import com.github.onotoliy.opposite.ui.events.EventNewScreen
import com.github.onotoliy.opposite.ui.events.EventsScreen
import com.github.onotoliy.opposite.ui.events.EventViewScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionEditScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionViewScreen
import com.github.onotoliy.opposite.ui.transactions.TransactionsTableScreen
import com.github.onotoliy.opposite.ui.users.UserEditScreen
import com.github.onotoliy.opposite.ui.users.UserViewScreen
import com.github.onotoliy.opposite.ui.users.UsersTableScreen

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
                EventNewScreen(
                    onSelect = { navController.navigate1(it) }
                )
            }

            composable("events") {
                EventsScreen(
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

                EventEditScreen(uuid)
            }

            composable("users") {
                UsersTableScreen(onSelect = { navController.navigate1(it) })
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
                UserEditScreen()
            }

            composable("transactions") {
                TransactionsTableScreen(onSelect = { navController.navigate1(it) })
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
                TransactionEditScreen()
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

        is Screen.UsersScreen -> "users"
        is Screen.UserEditScreen -> "users/${screen.uuid}/edit"
        is Screen.UserViewScreen -> "users/${screen.uuid}"
    }
    this.navigate(route)
}
