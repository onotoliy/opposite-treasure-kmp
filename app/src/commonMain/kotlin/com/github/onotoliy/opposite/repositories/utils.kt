package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.TransactionType
import com.github.onotoliy.opposite.data.User
import kotlin.String

fun newEvent(id: Int): Event {
    return Event(
        uuid = id.toString(),
        name = "Event $id",
        contribution = "1000",
        total = "1000",
        deadline = "2025-09-04",
        creationDate = "2025-09-04",
        author = Option("1", "Author"),
        deletionDate = null
    )
}

fun newUser(id: Int): User {
    return User(
        uuid = "$id",
        logo = "$id",
        login = "user-$id",
        name = "UserName $id",
        deposit = "1000",
        birthday = "14.06.1992",
        position = "Position $id",
        joiningDate = "14.06.1992"
    )
}
fun newTransaction(id: Int, eventID: Int? = null, personID: Int? = null): Transaction {
    return Transaction(
        uuid = "$id",
        name = "Transaction $id",
        type = TransactionType.CONTRIBUTION,
        cash = "10000",
        person = personID?.let { Option("$id", "Event $id") },
        event = eventID?.let { Option("$id", "User $id") },
        author = Option("$id", "Author $id") ,
        creationDate = "14.06.1992",
        transactionDate = "14.06.1992",
        deletionDate = null
    )
}


