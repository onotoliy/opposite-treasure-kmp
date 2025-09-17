package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.newEvent
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.AbstractEditModel
import com.github.onotoliy.opposite.repositories.EventRepository

class EventEditModel(
    private val repository: EventRepository,
    private val uuid: String
) : AbstractEditModel<Event>(uuid) {

    override suspend fun get(uuid: String): Event = repository.get(uuid)

    override suspend fun update(value: Event): Event = repository.update(value)
    override val defaultValue: Event
        get() = newEvent()

}
