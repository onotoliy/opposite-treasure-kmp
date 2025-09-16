package com.github.onotoliy.opposite.data

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Cashbox @OptIn(ExperimentalTime::class) constructor(
    val deposit: String,
    val lastUpdateDate: String
)