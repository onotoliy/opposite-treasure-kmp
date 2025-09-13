package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.TransactionCacheRepository.Companion.TRANSACTIONS
import kotlinx.coroutines.delay

class UserCacheRepository: IUserRepository {
    companion object {
        val USERS: MutableList<User> = (0..135).map { newUser(it) }.toMutableList()
    }

    override suspend fun get(uuid: String): User {
        delay(1000)
        return USERS.firstOrNull() { it.uuid == uuid } ?: USERS.first()
    }


    override suspend fun getAll(q: String?, offset: Int, numberOfRows: Int): List<User> {
        delay(1000)

        if (USERS.size < offset) {
            return emptyList()
        }

        if (USERS.size < offset + numberOfRows) {
            return USERS.subList(offset, USERS.size)
        }

        return USERS.subList(offset, offset + numberOfRows)
    }

    override suspend fun create(user: User): User {
        delay(1000)
        USERS.add(user)

        return get(user.uuid)
    }

    override suspend fun update(user: User): User {
        delay(1000)
        delete(user.uuid)
        create(user)

        return get(user.uuid)
    }

    override suspend fun delete(uuid: String) {
        delay(1000)
        val list = USERS.filter { it.uuid != uuid }

        USERS.clear()
        USERS.addAll(list)
    }
}