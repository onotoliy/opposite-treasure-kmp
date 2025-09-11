package com.github.onotoliy.opposite.ui.components.events.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.ui.components.events.views.EventListMobileView
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel

@Composable
actual fun EventListView(onLoadMore: () -> List<Event>, onSelect: (Screen) -> Unit) =
    EventListMobileView(onLoadMore, onSelect)