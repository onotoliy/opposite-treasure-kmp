package com.github.onotoliy.opposite.viewmodel.events

import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel
import com.github.opposite.treasure.shared.EventRepository

open class EventListModel(
    private val repository: EventRepository
) : AbstractInfinityListModel<Event>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Event> =
        repository.getAll(null, offset, numberOfRows)
}