package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebWindowScaffold(
    onSelect: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = { WebWindowHeader() },
            content = { paddingValues ->
                Row(modifier = Modifier.padding(paddingValues)) {
                    WebWindowSideMenu(onSelect)
                    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                        content()
                    }
                }
            }
        )
    }
}

@ExperimentalMaterial3Api
@Composable
private fun WebWindowHeader() {
    TopAppBar(
        title = { Text("Opposite MK") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun WebWindowSideMenu(
    onSelect: (Screen) -> Unit
) {
    Column(
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        WebWindowMenuButton(Icons.Outlined.Home, "Главная") {
            onSelect(Screen.CashScreen)
        }
        WebWindowMenuButton(Icons.Outlined.People, "Пользователи") {
            onSelect(Screen.UsersScreen)
        }
        WebWindowMenuButton(Icons.Outlined.Event, "События") {
            onSelect(Screen.EventsScreen)
        }
        WebWindowMenuButton(Icons.Outlined.CurrencyExchange, "Транзакции") {
            onSelect(Screen.TransactionsScreen)
        }

    }
}

@Composable
fun WebWindowMenuButton(
    imageVector: ImageVector,
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = label,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            modifier = Modifier.fillMaxWidth()
        )
    }
}