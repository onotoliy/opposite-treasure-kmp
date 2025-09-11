package com.github.onotoliy.opposite.data

import com.github.onotoliy.opposite.data.Option
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Event(
    val uuid: String,
    val name: String,
    val contribution: String,
    val total: String,
    val deadline: Instant,
    val creationDate: String,
    val author: Option,
    val deletionDate: String?
)
