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
import com.github.onotoliy.opposite.ui.components.scaffold.LocalNavHostController
import com.github.onotoliy.opposite.ui.events.screens.EventCreateScreen
import com.github.onotoliy.opposite.ui.events.screens.EventEditScreen
import com.github.onotoliy.opposite.ui.events.screens.EventListScreen
import com.github.onotoliy.opposite.ui.events.screens.EventViewScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionCreateScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionEditScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionViewScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionListScreen
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

    CompositionLocalProvider(
        LocalNavHostController provides navController,
    ) {
        NavHost(navController, startDestination = Rotes.CashScreen.path) {
            Rotes.values().forEach { rote ->
                composable(route = rote.path, arguments = rote.arguments) {
                    rote.content(it)
                }
            }
        }
    }


}

val NavBackStackEntry.uuid: String
    get() = savedStateHandle.get<String>("uuid")
        ?: throw IllegalArgumentException("UUID can not be empty")

fun NavController.goto(screen: Screen) {
    val route = when (screen) {
        is Screen.CashScreen -> Rotes.CashScreen.route()

        is Screen.EventNewScreen -> Rotes.EventNewScreen.route()
        is Screen.EventListScreen -> Rotes.EventListScreen.route()
        is Screen.EventEditScreen -> Rotes.EventEditScreen.route(mapOf("uuid" to screen.uuid))
        is Screen.EventViewScreen -> Rotes.EventViewScreen.route(mapOf("uuid" to screen.uuid))

        is Screen.TransactionNewScreen -> Rotes.TransactionNewScreen.route()
        is Screen.TransactionListScreen -> Rotes.TransactionListScreen.route()
        is Screen.TransactionEditScreen -> Rotes.TransactionEditScreen.route(mapOf("uuid" to screen.uuid))
        is Screen.TransactionViewScreen -> Rotes.TransactionViewScreen.route(mapOf("uuid" to screen.uuid))

        is Screen.UserNewScreen -> Rotes.UserNewScreen.route()
        is Screen.UserListScreen -> Rotes.UserListScreen.route()
        is Screen.UserEditScreen -> Rotes.UserEditScreen.route(mapOf("uuid" to screen.uuid))
        is Screen.UserViewScreen -> Rotes.UserViewScreen.route(mapOf("uuid" to screen.uuid))
    }
    this.navigate(route)
}
