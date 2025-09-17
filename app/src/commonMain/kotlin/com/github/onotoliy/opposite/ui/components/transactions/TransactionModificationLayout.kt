package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.lablel
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.DropdownMenu
import com.github.onotoliy.opposite.ui.SwaggestBox
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class, ExperimentalMaterial3Api::class)
@Composable
fun TransactionModificationLayout(
    type: Transaction.Type,
    isTypeEnable: Boolean,
    onTypeChanged: (Transaction.Type) -> Unit,

    name: String,
    onNameChanged: (String) -> Unit,

    cash: String,
    isCashEnable: Boolean,
    onCashChanged: (String) -> Unit,

    user: Option?,
    isUserEnable: Boolean,
    onUserChanged: (Option?) -> Unit,
    onUserQueryChanged: (String) -> List<Option>,

    event: Option?,
    isEventEnable: Boolean,
    onEventChanged: (Option?) -> Unit,
    onEventQueryChanged: (String) -> List<Option>,

    transactionDate: Instant,
    onTransactionDateChange: (Instant) -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        DropdownMenu(
            label = "Тип тразакции",
            enabled = isTypeEnable,
            options = Transaction.Type.values().map { Option(it.name, it.lablel) },
            onValueChange = { onTypeChanged(Transaction.Type.valueOf(it.uuid)) }
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Название") },
            value = name,
            onValueChange = onNameChanged
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            enabled = isCashEnable,
            label = { Text("Сумма") },
            value = cash,
            onValueChange = onCashChanged
        )

        SwaggestBox(
            label = "Позьзователь",
            enabled = isUserEnable,
            onSelected = onUserChanged,
            onQueryChanged = onUserQueryChanged
        )

        SwaggestBox(
            label = "Событие",
            enabled = isEventEnable,
            onSelected = onEventChanged,
            onQueryChanged = onEventQueryChanged
        )

        CalendarField(
            label = "Дата тразакции",
            value = transactionDate,
            onValueChanged = onTransactionDateChange
        )
    }
}
