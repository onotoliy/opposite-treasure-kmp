package com.github.opposite.treasure.shared

import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Option

interface IEventRepository {

    suspend fun get(uuid: String): Event

    suspend fun getOptionAll(): List<Option>

    suspend fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Event>

    suspend fun create(event: Event): Event

    suspend fun update(event: Event): Event

    suspend fun delete(uuid: String)
}
