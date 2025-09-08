package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.navigation.Screen
import io.github.windedge.table.DataTable

@Composable
fun TransactionTable(transactions: List<Transaction>, onSelect: (Screen) -> Unit) {
    DataTable(
        columns = {
            headerBackground {
                Box(modifier = Modifier.background(color = Color.LightGray))
            }
            column { Text("Тип") }
            column { Text("Название") }
            column { Text("Сумма") }
            column { Text("Пользователь") }
            column { Text("Событие") }
            column { Text("Дата создания") }
        }
    ) {
        transactions.forEach { record ->
            row(modifier = Modifier) {
                cell { Text(record.type.label) }
                cell {
                    Text(
                        modifier = Modifier.clickable {
                            onSelect(Screen.TransactionViewScreen(record.uuid))
                        },
                        text = record.name
                    )
                }
                cell { Text(record.cash) }
                cell {
                    Text(
                        modifier = Modifier.then(
                            record.person?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.UserViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = record.person?.name ?: ""
                    )
                }
                cell {
                    Text(
                        modifier = Modifier.then(
                            record.event?.uuid?.let {
                                Modifier.clickable {
                                    onSelect(Screen.EventViewScreen(it))
                                }
                            } ?: Modifier
                        ),
                        text = record.event?.name ?: ""
                    )
                }
                cell { Text(record.transactionDate) }
            }
        }
    }
}
