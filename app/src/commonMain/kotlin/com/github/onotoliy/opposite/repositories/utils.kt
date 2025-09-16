package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.treasure.model.Transaction.*
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalTime::class)
fun newEvent(
    uuid: String = "",
    name: String = "",
    contribution: String = "",
    deadline: Instant = Clock.System.now(),
    creationDate: Instant = Clock.System.now(),
    author: Option = Option("", ""),
    deletionDate: Instant? = null
) = Event( /* Уникальный иденитификатор */
    uuid = uuid,
    name = name,
    contribution = contribution,
    deadline = deadline,
    creationDate = creationDate,
    author = author,
    deletionDate = deletionDate
)

@OptIn(ExperimentalTime::class)
fun newTransaction(id: Int, eventID: Int? = null, personID: Int? = null): Transaction {
    return Transaction(uuid = "$id")
}

@OptIn(ExperimentalUuidApi::class, ExperimentalTime::class)
fun newDeposit(
    uuid: String = "",
    username: String = "",
    firstName: String = "",
    lastName: String = "",
    patronymic: String = "",
    deposit: String = "",
    email: String = "",
    birthday: kotlinx.datetime.Instant = Clock.System.now(),
    joiningDate: kotlinx.datetime.Instant = Clock.System.now(),
    position: Deposit.Position = Deposit.Position.NONE,
    logo: String? = null
) = Deposit(
    uuid = Uuid.random().toString(),
    username = username,
    firstName = firstName,
    lastName = lastName,
    patronymic = patronymic,
    deposit = deposit,
    email = email,
    birthday = birthday,
    joiningDate = joiningDate,
    position = position,
    logo = logo
)

val Type.lablel: String
    get() = when (this) {
        Type.NONE -> "Не выбрано"
        Type.COST -> "Расход"
        Type.CONTRIBUTION -> "Взнос"
        Type.WRITE_OFF -> "Списание с депозита"
        Type.PAID -> "Платеж"
        Type.EARNED -> "Заработано"
    }

val Deposit.Position.label: String
    get() = when (this) {
        Deposit.Position.PRESIDENT -> "Президент"
        Deposit.Position.VICE_PRESIDENT -> "Вице президент"
        Deposit.Position.TREASURER -> "Казнаяей"
        Deposit.Position.SECRETARY -> "Секретарь"
        Deposit.Position.MEMBER -> "Член клуба"
        Deposit.Position.FRIEND -> "Друг клуба"
        Deposit.Position.NONE -> "Не выбранно"
    }