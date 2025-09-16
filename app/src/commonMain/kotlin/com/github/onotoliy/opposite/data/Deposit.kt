package com.github.onotoliy.opposite.data

import com.github.onotoliy.opposite.treasure.model.Deposit
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Deposit @OptIn(ExperimentalTime::class) constructor(
    val uuid: String,
    val username: String,
    val firstName: String,
    val lastName: String,
    val patronymic: String,
    val deposit: String,
    val logo: String?,
    val email: String,
    val birthday: Instant,
    val joiningDate: Instant,
    val position: Deposit.Position
)
