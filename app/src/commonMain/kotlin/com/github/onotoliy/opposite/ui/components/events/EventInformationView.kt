package com.github.onotoliy.opposite.ui.components.events

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource

@Composable
expect fun EventInformationView(event: Event, logo: DrawableResource)