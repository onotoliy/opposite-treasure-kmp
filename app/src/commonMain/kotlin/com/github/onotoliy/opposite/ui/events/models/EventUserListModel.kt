package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import com.github.opposite.treasure.repositories.EventRepository

class EventUserListModel(
    private val deposits: DepositeRepository,
    private val events: EventRepository,
    private val eventID: String
): UserListModel(deposits) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Deposit> =
        events.getDebtors(eventID, offset, numberOfRows)
}