package com.github.onotoliy.opposite.ui.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.events.models.EventViewModel
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
actual fun EventInformationView(
    viewModel: EventViewModel
) = EventInformationMobileView(viewModel)
