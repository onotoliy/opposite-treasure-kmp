package com.github.onotoliy.opposite.data

data class Transaction(
    val uuid: String,
    val name: String,
    val cash: String,
    val type: TransactionType,
    val person: Option?,
    val event: Option?,
    val transactionDate: String,
    val creationDate: String,
    val author: Option,
    val deletionDate: String?
)