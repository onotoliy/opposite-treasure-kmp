package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.People
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
fun WebWindowScaffold(
    onSelect: (Screen) -> Unit,
    content: @Composable () -> Unit
) {
    MaterialTheme {
        Scaffold(
            topBar = { WebWindowHeader() },
            content = { paddingValues ->
                Row {
                    WebWindowSideMenu(onSelect)
                    content()
                }
            }
        )
    }
}

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
private fun WebWindowMenuButton(
    imageVector: ImageVector,
    contentDescription: String,
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
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = contentDescription,
            style = MaterialTheme.typography.button,
            modifier = Modifier.fillMaxWidth()
        )
    }
}