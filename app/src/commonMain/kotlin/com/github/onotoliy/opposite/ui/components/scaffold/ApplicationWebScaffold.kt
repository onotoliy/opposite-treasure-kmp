package com.github.onotoliy.opposite.ui.components.scaffold

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.components.buttons.LabeledIconButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApplicationWebScaffold(
    content: @Composable () -> Unit
) {
    val navHostController = LocalNavHostController.current

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
                    Column(
                        modifier = Modifier
                            .width(250.dp)
                            .fillMaxHeight()
                            .padding(16.dp)
                    ) {
                        LabeledIconButton(Icons.Outlined.Home, "Главная") {
                            navHostController.goto(Screen.CashScreen)
                        }
                        LabeledIconButton(Icons.Outlined.People, "Пользователи") {
                            navHostController.goto(Screen.UserListScreen)
                        }
                        LabeledIconButton(Icons.Outlined.Event, "События") {
                            navHostController.goto(Screen.EventListScreen)
                        }
                        LabeledIconButton(Icons.Outlined.CurrencyExchange, "Транзакции") {
                            navHostController.goto(Screen.TransactionListScreen)
                        }

                    }

                    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
                        content()
                    }
                }
            }
        )
    }
}

