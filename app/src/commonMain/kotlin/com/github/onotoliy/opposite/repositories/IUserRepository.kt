package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.User

interface IUserRepository {

    suspend fun get(uuid: String): User

    suspend fun getAll(q: String? = null, offset: Int = 0, numberOfRows: Int = 10): List<User>

    suspend fun create(user: User): User

    suspend fun update(user: User): User

    suspend fun delete(uuid: String)
}
