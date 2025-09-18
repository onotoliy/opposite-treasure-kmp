package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.ui.events.models.EventListAdapter
import com.github.onotoliy.opposite.ui.events.models.EventUserListModel
import com.github.onotoliy.opposite.ui.events.models.EventUserTableModel
import com.github.onotoliy.opposite.ui.users.models.UserListAdapter
import kotlin.uuid.Uuid

class UserEventListAdapter(
    private val userID: String,
    override val list: UserEventListModel,
    override val table: UserEventTableModel
): EventListAdapter(table, list)