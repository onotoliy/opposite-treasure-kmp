package com.github.onotoliy.opposite.ui.components.events.view

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
import com.github.onotoliy.opposite.ui.LabelledText
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun EventInformationMobileView(event: Event, logo: DrawableResource) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Название", event.name)
        LabelledText("Сумма", event.contribution)
        LabelledText("Сдать до", event.deadline)
        LabelledText("Автор", event.author.name)
        LabelledText("Дата создания", event.creationDate)

        Button(
            onClick = { /* обработка */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Сохранить")
        }

        Image(
            painter = painterResource(logo),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(300.dp)
        )
    }
}
