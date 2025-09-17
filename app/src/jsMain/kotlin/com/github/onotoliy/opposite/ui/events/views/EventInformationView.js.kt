package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun EventInformationView(
    event: Event,
    logo: DrawableResource,
    onSelect: (Screen) -> Unit
) = EventInformationWebView(event,  logo, onSelect)