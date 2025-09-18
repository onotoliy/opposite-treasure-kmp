package com.github.onotoliy.opposite.repositories

import com.github.onotoliy.opposite.treasure.client.infrastructure.HttpResponse
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.treasure.model.ExceptionInformation
import com.github.onotoliy.opposite.treasure.model.Option
import com.github.onotoliy.opposite.treasure.model.Transaction
import com.github.onotoliy.opposite.treasure.model.Transaction.Type
import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.readRemaining
import io.ktor.utils.io.readText
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlin.time.ExperimentalTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

const val NUMBER_OF_ROWS = 10

class HttpException(public val status: String = "500", public override val message: String): Exception(message)

@OptIn(InternalAPI::class)
suspend fun <T : Any> HttpResponse<T>.toRespose(): T {
    if (success) {
        return body()
    } else {
        val body = response.rawContent.readRemaining().readText()

        try {
            val info = Json { ignoreUnknownKeys = true }
                .decodeFromString(ExceptionInformation.serializer(), body)
            throw HttpException(info.status.name, info.message)
        } catch (e: SerializationException) {
            throw HttpException("INTERNAL_SERVER_ERROR", body)
        }
    }
}

fun Instant?.toPrettyString(): String {
    return this?.toString()?.substring(0, 10) ?: ""
}

fun String.toMoneyPrettyString(): String {
    if (isBlank()) {
        return ""
    }

    val parts = split(".")
    val whole = parts[0]
    val fraction = parts.getOrNull(1)
    val grouped = whole
        .reversed()
        .chunked(3)
        .joinToString(" ") { it }
        .reversed()

    return if (fraction != null) {
        "$grouped.$fraction"
    } else {
        grouped
    }
}

fun String.toPhonePrettyString(): String {
    val trimmed = if (length >= 10) substring(0..9) else this
    var out = if (trimmed.isNotEmpty()) "(" else ""

    for (i in trimmed.indices) {
        if (i == 3) out += ") "
        if (i == 6) out += " "
        out += trimmed[i]
    }

    return out
}

@OptIn(ExperimentalTime::class)
fun newEvent(
    uuid: String = "",
    name: String = "",
    contribution: String = "",
    deadline: Instant = Clock.System.now(),
    creationDate: Instant = Clock.System.now(),
    author: Option = Option("", ""),
    deletionDate: Instant? = null
) = Event(
    uuid = uuid,
    name = name,
    contribution = contribution,
    deadline = deadline,
    creationDate = creationDate,
    author = author,
    deletionDate = deletionDate
)

@OptIn(ExperimentalTime::class)
fun newTransaction(
    uuid: String = "",
    name: String = "",
    cash: String = "",
    type: Transaction.Type = Transaction.Type.NONE,
    transactionDate: kotlinx.datetime.Instant = Clock.System.now(),
    creationDate: kotlinx.datetime.Instant = Clock.System.now(),
    author: Option = Option("", ""),
    person: Option? = null,
    event: Option? = null,
    deletionDate: kotlinx.datetime.Instant? = null
) = Transaction(
    uuid = uuid,
    name = name,
    cash = cash,
    type = type,
    transactionDate = transactionDate,
    creationDate = creationDate,
    author = author,
    person = person,
    event = event,
    deletionDate = deletionDate
)

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

val Type.label: String
    get() = when (this) {
        Type.NONE -> "Не выбрано"
        Type.COST -> "Расход"
        Type.CONTRIBUTION -> "Взнос"
        Type.WRITE_OFF -> "Списание с депозита"
        Type.PAID -> "Платеж"
        Type.EARNED -> "Заработано"
    }

val Deposit.name: String
    get() {
        val f = firstName.firstOrNull()?.uppercase() ?: ""
        val p = patronymic.firstOrNull()?.uppercase() ?: ""
        val l = lastName

        return buildString {
            append(l)
            if (f.isNotEmpty()) append(" $f.")
            if (p.isNotEmpty()) append(" $p.")
        }
    }

val Deposit.Position.label: String
    get() = when (this) {
        Deposit.Position.PRESIDENT -> "Президент"
        Deposit.Position.VICE_PRESIDENT -> "Вице президент"
        Deposit.Position.TREASURER -> "Казначей"
        Deposit.Position.SECRETARY -> "Секретарь"
        Deposit.Position.MEMBER -> "Член клуба"
        Deposit.Position.FRIEND -> "Друг клуба"
        Deposit.Position.NONE -> "Не выбранно"
    }
