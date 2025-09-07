package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.User

interface IDebtRepository {

    suspend fun getDebtors(event: String): List<Event>

    suspend fun getDebts(user: String): List<User>

}