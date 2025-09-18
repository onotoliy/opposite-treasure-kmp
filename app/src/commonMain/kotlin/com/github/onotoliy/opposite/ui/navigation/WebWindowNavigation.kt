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

    NavHost(navController, startDestination = "cashbox") {
        composable("cashbox") {
            CashboxScreen(nav = navController)
        }

        composable("events/new") {
            EventCreateScreen(nav = navController)
        }

        composable("events") {
            EventListScreen(nav = navController)
        }

        composable(
            route = "events/{uuid}",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->
            EventViewScreen(uuid = backStackEntry.uuid, nav = navController)
        }

        composable(
            route = "events/{uuid}/edit",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->
            EventEditScreen(uuid = backStackEntry.uuid, nav = navController)
        }

        composable("users") {
            UserListScreen(nav = navController)
        }

        composable(route = "users/new") { backStackEntry ->
            UserCreateScreen(nav = navController)
        }

        composable(
            route = "users/{uuid}",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->
            UserViewScreen(uuid = backStackEntry.uuid, nav = navController)
        }

        composable(
            route = "users/{uuid}/edit",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->
            UserEditScreen(uuid = backStackEntry.uuid, nav = navController)
        }

        composable("transactions") {
            TransactionsTableScreen(nav = navController)
        }

        composable(route = "transactions/new") { backStackEntry ->
            TransactionCreateScreen(nav = navController)
        }

        composable(
            route = "transactions/{uuid}",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->
            TransactionViewScreen(uuid = backStackEntry.uuid, nav = navController)
        }

        composable(
            route = "transactions/{uuid}/edit",
            arguments = listOf(navArgument("uuid") { type = NavType.StringType })
        ) { backStackEntry ->

            TransactionEditScreen(uuid = backStackEntry.uuid, nav = navController)
        }
    }


}

private val NavBackStackEntry.uuid: String
    get() = savedStateHandle.get<String>("uuid")
        ?: throw IllegalArgumentException("UUID can not be empty")

fun NavController.goto(screen: Screen) {
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
