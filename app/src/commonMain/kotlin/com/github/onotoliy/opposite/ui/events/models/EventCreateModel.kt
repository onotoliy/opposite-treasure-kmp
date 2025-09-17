package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel
import com.github.onotoliy.opposite.repositories.EventRepository

class EventCreateModel(
    private val repository: EventRepository
) : AbstractCreateModel<Event>() {

    override suspend fun create(value: Event): Event = repository.create(value)
}
