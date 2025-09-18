package com.github.onotoliy.opposite.ui.events.models

import com.github.onotoliy.opposite.ui.users.models.UserListAdapter

class EventUserListAdapter(
    private val eventID: String,
    override val list: EventUserListModel,
    override val table: EventUserTableModel
): UserListAdapter(table, list)