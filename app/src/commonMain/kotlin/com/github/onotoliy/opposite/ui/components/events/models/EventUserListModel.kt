package com.github.onotoliy.opposite.ui.components.events.models

import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.users.UserListModel

class EventUserListModel(
    private val repository: IDepositeRepository,
    private val eventID: String
): UserListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Deposit> =
        repository.getAll(null, offset, numberOfRows)
}