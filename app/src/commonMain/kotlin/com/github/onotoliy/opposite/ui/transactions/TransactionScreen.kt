package com.github.onotoliy.opposite.ui.transactions

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold


@Composable
fun TransactionEditScreen() = TransactionScreen()

@Composable
private fun TransactionScreen() {
    ApplicationScaffold(
        onSelect = {  }
    ) {
        Text("TransactionScreen")
    }
}
