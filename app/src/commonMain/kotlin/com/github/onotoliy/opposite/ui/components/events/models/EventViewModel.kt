package com.github.onotoliy.opposite.viewmodel.events

import com.github.onotoliy.opposite.repositories.FileRepository
import com.github.onotoliy.opposite.repositories.newEvent
import com.github.onotoliy.opposite.treasure.model.Event
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import com.github.opposite.treasure.shared.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import opposite_treasure_kmp.app.generated.resources.Res
import opposite_treasure_kmp.app.generated.resources._29a98ff153997867b0760fd8d812f12478e180a3
import org.jetbrains.compose.resources.DrawableResource

class EventViewModel(
    private val events: EventRepository,
    private val files: FileRepository,
    private val uuid: String
) : AbstractViewModel<Event>() {

    private val _logo = MutableStateFlow<DrawableResource>(Res.drawable._29a98ff153997867b0760fd8d812f12478e180a3)

    val logo: StateFlow<DrawableResource> = _logo

    override suspend fun get(): Event = events.get(uuid)

    override suspend fun loadAdditionalValues() {
        _logo.value = files.download(uuid)
    }

    override val defaultValue: Event
        get() = newEvent()

}
