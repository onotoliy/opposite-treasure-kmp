package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.data.Deposit
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel

open class UserListModel(
    private val repository: IDepositeRepository
) : AbstractInfinityListModel<Deposit>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<Deposit> =
        repository.getAll(null, offset, numberOfRows)
}