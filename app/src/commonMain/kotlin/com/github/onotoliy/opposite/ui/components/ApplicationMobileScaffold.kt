package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationMobileScaffold(
    onSelect: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Opposite MK") },
                    modifier = Modifier.fillMaxWidth()
                )
            },
            content = { paddingValues ->
                Row(modifier = Modifier.padding(paddingValues)) {
                    content()
                }
            },
            bottomBar = {
                Row {
                    IconButton(onClick = { onSelect(Screen.CashScreen) }) {
                        Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
                    }
                    IconButton(onClick = { onSelect(Screen.UsersScreen) }) {
                        Icon(imageVector = Icons.Outlined.People, contentDescription = "Home")
                    }
                    IconButton(onClick = { onSelect(Screen.EventsScreen) }) {
                        Icon(imageVector = Icons.Outlined.Event, contentDescription = "Home")
                    }
                    IconButton(onClick = { onSelect(Screen.TransactionsScreen) }) {
                        Icon(imageVector = Icons.Outlined.CurrencyExchange, contentDescription = "Home")
                    }
                }
            }
        )
    }
}

