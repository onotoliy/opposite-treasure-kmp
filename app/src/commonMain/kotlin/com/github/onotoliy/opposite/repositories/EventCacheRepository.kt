package com.github.opposite.treasure.shared

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.repositories.newEvent
import kotlinx.coroutines.delay
import kotlin.String
import kotlin.time.Clock
import kotlin.time.Instant



class EventCacheRepository : IEventRepository {

    companion object {
        val EVENTS: MutableList<Event> = mutableListOf<Event>(
            newEvent(1), newEvent(2), newEvent(3), newEvent(4)
        )
    }

    override suspend fun get(uuid: String): Event {
        delay(1000)
        return EVENTS.firstOrNull() { it.uuid == uuid } ?: EVENTS.first()
    }

    override suspend fun getOptionAll(): List<Option> {
        delay(1000)
        return EVENTS.map { Option(it.uuid, it.name) }
    }

    override suspend fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Event> {
        delay(1000)
        return EVENTS
    }

    override suspend fun create(event: Event): Event {
        delay(1000)
        EVENTS.add(event)

        return get(event.uuid)
    }

    override suspend fun update(event: Event): Event {
        delay(1000)
        delete(event.uuid)
        create(event)

        return get(event.uuid)
    }

    override suspend fun delete(uuid: String) {
        delay(1000)
        val list = EVENTS.filter { it.uuid != uuid }

        EVENTS.clear()
        EVENTS.addAll(list)
    }
}