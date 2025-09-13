package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel

class UserCreateModel(
    private val repository: IUserRepository
) : AbstractCreateModel<User>() {

    override suspend fun create(value: User): User = repository.create(value)

}
