package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.components.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime

@Composable
expect fun TransactionInformationView(transaction: Transaction, onSelect: (Screen) -> Unit)

@Composable
fun TransactionInformationMobileView(transaction: Transaction, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            onSelect(
                Screen.TransactionEditScreen(transaction.uuid)
            )
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TransactionInformationLayout(transaction, onSelect)
    }
}
@OptIn(ExperimentalTime::class)
@Composable
fun TransactionInformationWebView(transaction: Transaction, onSelect: (Screen) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TransactionInformationLayout(transaction, onSelect)

        Button(
            onClick = { /* обработка */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Сохранить")
        }
    }
}