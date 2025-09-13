package com.github.onotoliy.opposite.viewmodel.events

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel
import com.github.opposite.treasure.shared.IEventRepository

class EventCreateModel(
    private val repository: IEventRepository
) : AbstractCreateModel<Event>() {

    override suspend fun create(value: Event): Event = repository.create(value)
}
