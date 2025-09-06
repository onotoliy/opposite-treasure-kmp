package com.github.onotoliy.opposite.data

import com.github.onotoliy.opposite.data.Option

data class Event(
    val uuid: String,
    val name: String,
    val contribution: String,
    val total: String,
    val deadline: String,
    val creationDate: String,
    val author: Option,
    val deletionDate: String?
)
