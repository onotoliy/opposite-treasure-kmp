package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.repositories.toRespose
import com.github.onotoliy.opposite.treasure.api.EventResourceApi
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.PageDeposit
import com.github.onotoliy.opposite.treasure.model.PageEvent
import kotlin.String

class EventRepository(private val api: EventResourceApi) {

    suspend fun get(uuid: String): Event {
        return api.getEventResource(uuid).toRespose()
    }

    suspend fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): PageEvent {
        return api.getAllEventResource(name, offset, numberOfRows).toRespose()
    }

    suspend fun getDebtors(
        event: String,
        offset: Int,
        numberOfRows: Int
    ): PageDeposit {
        return api.getDebtorsEventResource(event, offset, numberOfRows).toRespose()
    }

    suspend fun create(event: Event): Event {
        return api.createEventResource(event).toRespose()
    }

    suspend fun update(event: Event): Event {
        return api.updateEventResource(event).toRespose()
    }

    suspend fun delete(uuid: String) {
        api.deleteEventResource(uuid)
    }
}
