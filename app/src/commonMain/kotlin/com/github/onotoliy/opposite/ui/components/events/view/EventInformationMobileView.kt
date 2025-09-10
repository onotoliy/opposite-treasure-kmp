package com.github.onotoliy.opposite.ui.components.events.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
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
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LabelledText("Название", event.name)
        LabelledText("Сумма", event.contribution)
        LabelledText("Сдать до", event.deadline)

        ElevatedCard(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(logo),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            )
        }
    }
}
