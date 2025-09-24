package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import com.github.onotoliy.opposite.repositories.EventRepository
import com.github.onotoliy.opposite.ui.users.models.UserTableModel
import com.github.onotoliy.opposite.viewmodel.Page

class EventUserTableModel(
    private val deposits: DepositeRepository,
    private val events: EventRepository,
    private val eventID: String
): UserTableModel(deposits) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Deposit> {
        val page = events.getDebtors(eventID, offset, numberOfRows)

        return Page(page.meta.total, page.context)
    }
}
