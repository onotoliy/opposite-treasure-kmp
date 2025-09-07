package com.github.onotoliy.opposite.viewmodel.events

import com.github.opposite.treasure.shared.IEventRepository

class EventEditModel(
    private val repository: IEventRepository,
    private val uuid: String
) : EventViewModel(repository, uuid)
