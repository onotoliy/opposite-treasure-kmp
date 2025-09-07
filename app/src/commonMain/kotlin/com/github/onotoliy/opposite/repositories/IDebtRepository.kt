package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.User

interface IDebtRepository {

    suspend fun getDebtors(eventID: String): List<User>

    suspend fun getDebts(userID: String): List<Event>

}