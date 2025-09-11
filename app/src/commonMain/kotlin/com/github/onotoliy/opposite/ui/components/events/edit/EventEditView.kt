package com.github.onotoliy.opposite.ui.components.events.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.components.SaveFloatingActionButton
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun EventEditView(viewModel: EventNewViewModel, onSelect: (Screen) -> Unit) {
    var name by remember { mutableStateOf("") }
    var contribution by remember { mutableStateOf("") }
    var deadline by remember { mutableStateOf(Clock.System.now()) }

    LocalMobileScafoldState.current.topBar = { Text("Создание мероприятия") }
    LocalMobileScafoldState.current.floatingActionButton = {
        SaveFloatingActionButton {
            viewModel.onSave(name, contribution, deadline) {
                onSelect(Screen.EventViewScreen(it.uuid))
            }
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Название") },
            value = name,
            onValueChange = { name = it }
        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Сумма") },
            value = contribution,
            onValueChange = { contribution = it }
        )

        CalendarField(
            label = "Сдать до",
            value = deadline,
            onValueChanged = { deadline = it }
        )
    }
}

