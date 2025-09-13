package com.github.onotoliy.opposite.data

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class User @OptIn(ExperimentalTime::class) constructor(
    val uuid: String,
    val logo: String,
    val login: String,
    val name: String,
    val deposit: String,
    val birthday: Instant,
    val joiningDate: Instant,
    val position: String
)
