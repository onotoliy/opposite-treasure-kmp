package com.github.onotoliy.opposite.ui.components.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString
import com.github.onotoliy.opposite.repositories.toPrettyString
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.ui.components.LabelledText
import com.github.onotoliy.opposite.ui.navigation.Screen
import kotlinx.datetime.Instant
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun EventInformationLayout(
    event: Event, onSelect: (Screen) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Название", event.name)
        LabelledText("Сумма взноса", event.contribution.toMoneyPrettyString())
        LabelledText("До какого числа сдать", event.deadline.toPrettyString())
        LabelledText("Дата создания", event.creationDate.toPrettyString())
        LabelledText("author", event.author.name) {
            onSelect(Screen.UserViewScreen(event.author.uuid))
        }
    }
}