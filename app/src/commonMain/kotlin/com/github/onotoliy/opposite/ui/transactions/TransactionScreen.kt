package com.github.onotoliy.opposite.ui.transactions

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun TransactionViewScreen() = TransactionScreen()

@Composable
fun TransactionEditScreen() = TransactionScreen()

@Composable
private fun TransactionScreen() {
    Text("TransactionScreen")
}
