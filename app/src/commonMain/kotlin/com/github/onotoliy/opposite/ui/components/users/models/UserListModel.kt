package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.AbstractInfinityListModel

open class UserListModel(
    private val repository: IUserRepository
) : AbstractInfinityListModel<User>() {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<User> =
        repository.getAll(null, offset, numberOfRows)
}