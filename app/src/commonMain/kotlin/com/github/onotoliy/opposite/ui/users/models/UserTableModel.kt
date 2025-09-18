package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityTableModel
import com.github.onotoliy.opposite.viewmodel.Page

open class UserTableModel(
    private val repository: DepositeRepository
) : AbstractInfinityTableModel<Deposit>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): Page<Deposit> {
        val page = repository.getAll(null, offset, numberOfRows)

        return Page(page.meta.total, page.context)
    }
}
