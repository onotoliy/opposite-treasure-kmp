package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel

open class UserListModel(
    private val repository: IDepositeRepository
) : AbstractInfinityListModel<Deposit>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Deposit> =
        repository.getAll(null, offset, numberOfRows)
}