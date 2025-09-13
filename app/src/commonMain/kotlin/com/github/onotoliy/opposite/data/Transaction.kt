package com.github.onotoliy.opposite.data

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

data class Transaction @OptIn(ExperimentalTime::class) constructor(
    val uuid: String,
    val name: String,
    val cash: String,
    val type: TransactionType,
    val person: Option?,
    val event: Option?,
    val transactionDate: Instant,
    val creationDate: Instant,
    val author: Option,
    val deletionDate: Instant?
)