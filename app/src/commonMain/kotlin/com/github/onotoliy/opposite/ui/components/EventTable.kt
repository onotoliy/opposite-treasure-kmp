package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.navigation.Screen
import io.github.windedge.table.DataTable

@Composable
fun EventTable(events: List<Event>, onSelect: (Screen) -> Unit) {
    DataTable(
        columns = {
            headerBackground {
                Box(modifier = Modifier.background(color = Color.LightGray))
            }
            column { Text("Название") }
            column { Text("Сумма") }
            column { Text("Сдать до") }
        }
    ) {
        events.forEach { record ->
            row(modifier = Modifier) {
                cell {
                    Text(
                        modifier = Modifier.clickable{
                            onSelect(Screen.EventViewScreen(record.uuid))
                        },
                        text = record.name
                    )
                }
                cell { Text(record.contribution) }
                cell { Text(record.deadline) }
            }

        }
    }
}
