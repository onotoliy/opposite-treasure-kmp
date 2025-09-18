package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.events.models.EventListModel
import com.github.onotoliy.opposite.repositories.EventRepository

class UserEventListModel(
    private val events: EventRepository,
    private val deposits: DepositeRepository,
    private val userID: String
): EventListModel(events) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Event> =
        deposits.getDebts(userID, offset, numberOfRows)
}
