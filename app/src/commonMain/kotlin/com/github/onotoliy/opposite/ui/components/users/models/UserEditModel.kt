package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.repositories.newUser
import com.github.onotoliy.opposite.viewmodel.AbstractEditModel

class UserEditModel(
    private val repository: IUserRepository,
    private val uuid: String
): AbstractEditModel<User>(uuid) {

    override suspend fun get(uuid: String): User = repository.get(uuid)

    override suspend fun update(value: User): User = repository.update(value)
    override val defaultValue: User
        get() = newUser(-1000)

}
