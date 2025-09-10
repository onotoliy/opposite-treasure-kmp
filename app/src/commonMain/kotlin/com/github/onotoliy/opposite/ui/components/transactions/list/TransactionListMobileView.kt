package com.github.onotoliy.opposite.ui.components.transactions.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowDownward
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.ArrowUpward
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.CurrencyExchange
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
fun TransactionListMobileView(transactions: List<Transaction>, onSelect: (Screen) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(transactions.size) {
            TransactionMobileItem(transactions[it], onSelect)
        }
    }
}

@Composable
private fun TransactionMobileItem(transaction: Transaction, onSelect: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable { onSelect(Screen.TransactionViewScreen(transaction.uuid)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = when (transaction.type) {
            TransactionType.NONE -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.COST -> Icons.Outlined.ArrowDownward
            TransactionType.CONTRIBUTION -> Icons.Outlined.ArrowOutward
            TransactionType.WRITE_OFF -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.PAID -> Icons.AutoMirrored.Outlined.ArrowForward
            TransactionType.EARNED -> Icons.Outlined.ArrowOutward
        }
        val color = when (transaction.type) {
            TransactionType.NONE -> Color.Black
            TransactionType.COST -> Color.Red
            TransactionType.CONTRIBUTION -> Color.Green
            TransactionType.WRITE_OFF -> Color.Black
            TransactionType.PAID -> Color.Red
            TransactionType.EARNED -> Color.Green
        }
        Icon(imageVector = icon, contentDescription = null)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.name)
                Text(color = color, text = transaction.cash)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = transaction.person?.name ?: "")
                Text(text = transaction.transactionDate)
            }

            HorizontalDivider()
        }
    }
}