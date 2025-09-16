package com.github.onotoliy.opposite.data

import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.treasure.model.Event
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class Event(
    val uuid: String,
    val name: String,
    val contribution: String,
    val deadline: Instant,
    val creationDate: Instant,
    val author: Option,
    val deletionDate: Instant?
)

