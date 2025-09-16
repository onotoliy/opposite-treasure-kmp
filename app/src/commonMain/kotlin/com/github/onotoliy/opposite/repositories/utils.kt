package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import kotlinx.datetime.Clock
import kotlinx.serialization.SerialName
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun newEvent(id: Int): Event {
    return Event(uuid = "$id")
}

@OptIn(ExperimentalTime::class)
fun newUser(id: Int): Deposit {
    return Deposit(uuid = "$id")
}

@OptIn(ExperimentalTime::class)
fun newTransaction(id: Int, eventID: Int? = null, personID: Int? = null): Transaction {
    return Transaction(uuid = "$id")
}


