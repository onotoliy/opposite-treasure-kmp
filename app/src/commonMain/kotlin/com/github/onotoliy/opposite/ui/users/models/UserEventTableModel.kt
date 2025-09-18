package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.ui.events.models.EventListModel
import com.github.onotoliy.opposite.repositories.EventRepository
import com.github.onotoliy.opposite.ui.events.models.EventTableModel
import com.github.onotoliy.opposite.viewmodel.Page

class UserEventTableModel(
    private val events: EventRepository,
    private val deposits: DepositeRepository,
    private val userID: String
) : EventTableModel(events) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Event> {
        val page = deposits.getDebts(userID, offset, numberOfRows)

        return Page(page.meta.total, page.context)
    }
}
