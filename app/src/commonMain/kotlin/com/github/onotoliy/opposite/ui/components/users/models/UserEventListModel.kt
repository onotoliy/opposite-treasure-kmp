package com.github.onotoliy.opposite.ui.components.users.models

import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.events.EventListModel
import com.github.opposite.treasure.shared.IEventRepository

class UserEventListModel(
    private val repository: IEventRepository,
    private val userID: String
): EventListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Event> =
        repository.getAll(null, offset, numberOfRows)
}