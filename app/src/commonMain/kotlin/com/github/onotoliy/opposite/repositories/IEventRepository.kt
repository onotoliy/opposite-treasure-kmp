package com.github.opposite.treasure.shared

import com.github.onotoliy.opposite.treasure.api.EventResourceApi
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import kotlin.String

class IEventRepository(private val api: EventResourceApi) {

    suspend fun get(uuid: String): Event {
        return api.getEventResource(uuid).body()
    }

    suspend fun getOptionAll(): List<Option> {
        return api.getAllEventResource().body().context?.map {
            Option(
                it.uuid ?: "",
                it.name ?: ""
            )
        } ?: listOf()
    }

    suspend fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Event> {
        return api.getAllEventResource(name, offset, numberOfRows).body().context ?: listOf()
    }

    suspend fun create(event: Event): Event {
        return api.createEventResource(event).body()
    }

    suspend fun update(event: Event): Event {
        return api.updateEventResource(event).body()
    }

    suspend fun delete(uuid: String) {
        api.deleteEventResource(uuid)
    }
}
