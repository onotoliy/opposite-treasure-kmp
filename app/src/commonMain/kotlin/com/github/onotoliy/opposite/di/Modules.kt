package com.github.onotoliy.opposite.di

import com.github.onotoliy.opposite.repositories.CashboxCacheRepository
import com.github.onotoliy.opposite.repositories.DebtCacheRepository
import com.github.onotoliy.opposite.repositories.UserCacheRepository
import com.github.onotoliy.opposite.repositories.FileCacheRepository
import com.github.onotoliy.opposite.repositories.ICashboxRepository
import com.github.onotoliy.opposite.repositories.IDebtRepository
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.repositories.TransactionCacheRepository
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import com.github.opposite.treasure.shared.EventCacheRepository
import com.github.opposite.treasure.shared.IEventRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<ICashboxRepository> { CashboxCacheRepository() }
    single<IUserRepository> { UserCacheRepository() }
    single<IDebtRepository> { DebtCacheRepository() }
    single<ITransactionRepository> { TransactionCacheRepository() }
    single<IEventRepository> { EventCacheRepository() }
    single<IFileRepository> { FileCacheRepository() }
}

val viewModelModule = module {
    factory { (id: String) -> EventViewModel(get(), get(), id) }
    factory { (id: String) -> EventEditModel(get(), id) }
    single { EventsListModel(get()) }
}