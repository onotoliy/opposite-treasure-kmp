package com.github.opposite.treasure.shared

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
