package com.github.onotoliy.opposite.data

data class Page<T>(
    val meta: Meta,
    val context: List<T>
)

data class Meta(
    val total: Int,
    val paging: Paging
)

data class Paging(
    val start: Int,
    val size: Int
)