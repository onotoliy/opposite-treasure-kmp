package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.lablel
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.navigation.Screen
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun TransactionInformationLayout(transaction: Transaction, onSelect: (Screen) -> Unit) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Тип", transaction.type.lablel)
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
        LabelledText("Дата транзакции", transaction.transactionDate.toString())
        LabelledText("Автор", transaction.author.name)
        LabelledText("Дата создания", transaction.creationDate.toString())
    }
}
