package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.EventRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityTableModel
import com.github.onotoliy.opposite.viewmodel.Page

open class EventTableModel(
    private val repository: EventRepository
) : AbstractInfinityTableModel<Event>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Event> {
        val page = repository.getAll(null, offset, numberOfRows)

        return Page(page.meta.total, page.context)
    }
}