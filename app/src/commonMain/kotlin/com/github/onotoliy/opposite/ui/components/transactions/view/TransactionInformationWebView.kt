package com.github.onotoliy.opposite.ui.components.transactions.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TransactionInformationWebView(transaction: Transaction, onSelect: (Screen) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Тип", transaction.type.label)
        LabelledText("Название", transaction.name)
        LabelledText("Сумма", transaction.cash)
        transaction.person?.let {
            LabelledText("Пользователь", it.name) {
                onSelect(Screen.UserViewScreen(it.uuid))
            }
        }
        transaction.event?.let {
            LabelledText("Событие", it.name) {
                onSelect(Screen.EventViewScreen(it.uuid))
            }
        }
        LabelledText("Дата транзакции", transaction.transactionDate)
        LabelledText("Автор", transaction.author.name)
        LabelledText("Дата создания", transaction.creationDate)

        Button(
            onClick = { /* обработка */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Сохранить")
        }
    }
}
