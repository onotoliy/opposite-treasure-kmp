package com.github.onotoliy.opposite.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.github.onotoliy.opposite.ui.cashbox.screens.CashboxScreen
import com.github.onotoliy.opposite.ui.events.screens.EventCreateScreen
import com.github.onotoliy.opposite.ui.events.screens.EventEditScreen
import com.github.onotoliy.opposite.ui.events.screens.EventListScreen
import com.github.onotoliy.opposite.ui.events.screens.EventViewScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionCreateScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionEditScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionListScreen
import com.github.onotoliy.opposite.ui.transactions.screens.TransactionViewScreen
import com.github.onotoliy.opposite.ui.users.screens.UserCreateScreen
import com.github.onotoliy.opposite.ui.users.screens.UserEditScreen
import com.github.onotoliy.opposite.ui.users.screens.UserListScreen
import com.github.onotoliy.opposite.ui.users.screens.UserViewScreen
import kotlin.collections.listOf
import kotlin.reflect.KClass

enum class Rotes(
    val path: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavBackStackEntry) -> Unit
) {

    CashScreen("cashbox",  content = { CashboxScreen() }),

    EventListScreen("events", content = { EventListScreen() }),

    EventNewScreen("events/new", content = { EventCreateScreen() }),

    EventViewScreen(
        path = "event/{uuid}",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { EventViewScreen(uuid = it.uuid) }
    ),

    EventEditScreen(
        path = "event/{uuid}/edit",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { EventEditScreen(uuid = it.uuid) }
    ),

    UserListScreen("users", content = { UserListScreen() }),

    UserNewScreen("users/new", content = { UserCreateScreen() }),

    UserViewScreen(
        path = "user/{uuid}",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { UserViewScreen(uuid = it.uuid) }
    ),

    UserEditScreen(
        path = "user/{uuid}/edit",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { UserEditScreen(uuid = it.uuid) }
    ),

    TransactionListScreen("transactions", content = { TransactionListScreen() }),

    TransactionNewScreen("transactions/new", content = { TransactionCreateScreen() }),

    TransactionViewScreen(
        path = "transaction/{uuid}",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { TransactionViewScreen(uuid = it.uuid) }
    ),

    TransactionEditScreen(
        path = "transaction/{uuid}/edit",
        arguments = listOf(navArgument("uuid") { type = NavType.StringType }),
        content = { TransactionEditScreen(uuid = it.uuid) }
    );

    fun route(arguments: Map<String, String> = emptyMap()): String {
        var result = path

        arguments.entries.forEach {
            result = result.replaceFirst("{${it.key}}", it.value)
        }

        return result
    }

}
