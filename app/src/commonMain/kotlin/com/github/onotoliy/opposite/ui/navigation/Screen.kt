package com.github.onotoliy.opposite.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {

    @Serializable
    object CashScreen : Screen();

    @Serializable
    object EventsScreen : Screen();

    @Serializable
    data class EventViewScreen(val uuid: String) : Screen();

    @Serializable
    data class EventEditScreen(val uuid: String) : Screen();

    @Serializable
    object UsersScreen : Screen();

    @Serializable
    data class UserViewScreen(val uuid: String) : Screen();

    @Serializable
    data class UserEditScreen(val uuid: String) : Screen();

    @Serializable
    object TransactionsScreen : Screen();

    @Serializable
    data class TransactionViewScreen(val uuid: String) : Screen();

    @Serializable
    data class TransactionEditScreen(val uuid: String) : Screen();
}
