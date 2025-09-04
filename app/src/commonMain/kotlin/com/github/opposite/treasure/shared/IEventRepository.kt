package com.github.opposite.treasure.shared

interface IEventRepository {

    fun get(uuid: String): Event

    fun getOptionAll(): List<Option>

    fun getAll(
        name: String?,
        offset: Int,
        numberOfRows: Int
    ): List<Event>

    fun create(event: Event): Event

    fun update(event: Event): Event

    fun delete(uuid: String)

}
