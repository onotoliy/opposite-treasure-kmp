package com.github.onotoliy.opposite.ui.components.users.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.navigation.Screen
import io.github.windedge.table.DataTable

@Composable
fun WebUserTableComponent(users: List<User>, onSelect: (Screen) -> Unit) {
    DataTable(
        columns = {
            headerBackground {
                Box(modifier = Modifier.background(color = Color.LightGray))
            }
            column { Text("ФИО") }
            column { Text("Депозит") }
        }
    ) {
        users.forEach { record ->
            row(modifier = Modifier) {
                cell {
                    Text(
                        modifier = Modifier.clickable {
                            onSelect(Screen.UserViewScreen(record.uuid))
                        },
                        text = record.name
                    )
                }
                cell { Text(record.deposit) }
            }
        }
    }
}
