package com.github.onotoliy.opposite.ui.components.events

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.components.events.view.EventInformationMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun EventInformationView(
    event: Event,
    logo: DrawableResource
) = EventInformationMobileView(event,  logo)