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
import com.github.onotoliy.opposite.viewmodel.cashbox.CashboxViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventNewViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventsListModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionViewModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionsListModel
import com.github.onotoliy.opposite.viewmodel.users.UserViewModel
import com.github.onotoliy.opposite.viewmodel.users.UsersListModel
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
    factory { (id: String) -> EventViewModel(get(), get(), get(), get(), id) }
    factory { (id: String) -> EventEditModel(get(), id) }
    single { EventsListModel(get()) }
    single { EventNewViewModel(get()) }

    factory { (id: String) -> TransactionViewModel(get(), get(), id) }
    single { TransactionsListModel(get()) }
    factory { (id: String) -> UserViewModel(get(), get(), get(), get(), id) }
    single { UsersListModel(get()) }
    single { CashboxViewModel(get()) }
}