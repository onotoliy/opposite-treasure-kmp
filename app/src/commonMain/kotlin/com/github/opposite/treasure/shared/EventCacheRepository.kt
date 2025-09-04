package com.github.opposite.treasure.shared

class EventCacheRepository: IEventRepository {

    companion object {
        private val EVENTS: MutableList<Event> = mutableListOf<Event>()
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