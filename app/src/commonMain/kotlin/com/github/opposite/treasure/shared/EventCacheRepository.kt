package com.github.opposite.treasure.shared

import kotlin.String
import kotlin.time.Clock
import kotlin.time.Instant

private fun newEvent(id: Int): Event {
    return Event(
        uuid = id.toString(),
        name = "Event $id",
        contribution = "1000",
        total = "1000",
        deadline = "2025-09-04",
        creationDate = "2025-09-04",
        author = Option("1", "Author"),
        deletionDate = null
    )
}

class EventCacheRepository : IEventRepository {

    companion object {
        private val EVENTS: MutableList<Event> = mutableListOf<Event>(
            newEvent(1), newEvent(2), newEvent(3), newEvent(4)
        )
    }

    override fun get(uuid: String): Event {
        return EVENTS.first { it.uuid == uuid }
    }

    override fun getOptionAll(): List<Option> {
        return EVENTS.map { Option(it.uuid, it.name) }
    }

    override fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Event> {
        return EVENTS
    }

    override fun create(event: Event): Event {
        EVENTS.add(event)

        return get(event.uuid)
    }

    override fun update(event: Event): Event {
        delete(event.uuid)
        create(event)

        return get(event.uuid)
    }

    override fun delete(uuid: String) {
        val list = EVENTS.filter { it.uuid != uuid }

        EVENTS.clear()
        EVENTS.addAll(list)
    }
}