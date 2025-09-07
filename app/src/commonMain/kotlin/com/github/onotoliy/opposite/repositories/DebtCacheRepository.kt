package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.User

class DebtCacheRepository : IDebtRepository {
    override suspend fun getDebtors(event: String): List<Event> {
        return (0..10).map { newEvent(it) }
    }

    override suspend fun getDebts(user: String): List<User> {
        return (0..10).map { newUser(it) }
    }
}