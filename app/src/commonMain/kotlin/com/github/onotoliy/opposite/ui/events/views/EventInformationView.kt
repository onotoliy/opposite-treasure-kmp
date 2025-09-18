package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.components.buttons.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.events.EventInformationLayout
import com.github.onotoliy.opposite.ui.components.scaffold.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime

@Composable
expect fun EventInformationView(event: Event, logo: DrawableResource, onSelect: (Screen) -> Unit)

@Composable
@OptIn(ExperimentalTime::class)
fun EventInformationMobileView(event: Event, logo: DrawableResource, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.topBar = { Text(event.name) }
    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            onSelect(
                Screen.EventEditScreen(event.uuid)
            )
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        EventInformationLayout(event, onSelect)

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

@Composable
@OptIn(ExperimentalTime::class)
fun EventInformationWebView(event: Event, logo: DrawableResource, onSelect: (Screen) -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(logo),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(300.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            EventInformationLayout(event, onSelect)

            Button(
                onClick = { /* обработка */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Сохранить")
            }
        }
    }
}
