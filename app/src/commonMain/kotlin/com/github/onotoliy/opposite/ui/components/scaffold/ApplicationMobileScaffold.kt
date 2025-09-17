package com.github.onotoliy.opposite.ui.components.scaffold

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.github.onotoliy.opposite.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationMobileScaffold(
    onSelect: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    val mobileScafoldState = remember { MobileScafoldState() }

    CompositionLocalProvider(LocalMobileScafoldState provides mobileScafoldState) {
        MaterialTheme {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = { mobileScafoldState.topBar?.invoke() ?: Text("Opposite MK") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                floatingActionButton = { mobileScafoldState.floatingActionButton?.invoke() },
                content = { paddingValues ->
                    Row(modifier = Modifier.padding(paddingValues)) {
                        content()
                    }
                },
                bottomBar = {
                    NavigationBar {
                        NavigationBarItem(
                            selected = false,
                            onClick = { onSelect(Screen.CashScreen) },
                            icon = { Icon(Icons.Outlined.Home, contentDescription = "Cash") },
                            label = { Text("Cash") }
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { onSelect(Screen.UsersScreen) },
                            icon = { Icon(Icons.Outlined.People, contentDescription = "Users") },
                            label = { Text("Users") }
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { onSelect(Screen.EventsScreen) },
                            icon = { Icon(Icons.Outlined.Event, contentDescription = "Events") },
                            label = { Text("Events") }
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = { onSelect(Screen.TransactionsScreen) },
                            icon = {
                                Icon(
                                    Icons.Outlined.CurrencyExchange,
                                    contentDescription = "Transactions"
                                )
                            },
                            label = { Text("Transactions") }
                        )
                    }
                }
            )
        }
    }
}

