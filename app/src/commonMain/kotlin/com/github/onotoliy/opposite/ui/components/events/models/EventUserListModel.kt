package com.github.onotoliy.opposite.ui.components.events.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.users.UserListModel
import com.github.opposite.treasure.shared.EventRepository

class EventUserListModel(
    private val deposits: DepositeRepository,
    private val events: EventRepository,
    private val eventID: String
): UserListModel(deposits) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Deposit> =
        events.getDebtors(eventID, offset, numberOfRows)
}