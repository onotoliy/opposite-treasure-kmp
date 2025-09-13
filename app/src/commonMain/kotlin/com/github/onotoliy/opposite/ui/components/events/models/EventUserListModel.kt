package com.github.onotoliy.opposite.ui.components.events.models

import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.users.UserListModel

class EventUserListModel(
    private val repository: IUserRepository,
    private val eventID: String
): UserListModel(repository) {

    override suspend fun getAll(offset: Int, numberOfRows: Int): List<User> =
        repository.getAll(null, offset, numberOfRows)
}