package com.github.onotoliy.opposite.di

import com.github.onotoliy.opposite.ui.events.EventEditScreen
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import com.github.opposite.treasure.shared.EventCacheRepository
import com.github.opposite.treasure.shared.IEventRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<IEventRepository> { EventCacheRepository() }
}

val viewModelModule = module {
    factory { (id: String) -> EventViewModel(get(), id) }
    factory { (id: String) -> EventEditModel(get(), id) }
    single { EventsListModel(get()) }
}