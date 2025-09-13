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
import com.github.onotoliy.opposite.ui.components.events.models.EventTransactionListModel
import com.github.onotoliy.opposite.ui.components.events.models.EventUserListModel
import com.github.onotoliy.opposite.ui.components.users.models.UserEventListModel
import com.github.onotoliy.opposite.ui.components.users.models.UserTransactionListModel
import com.github.onotoliy.opposite.viewmodel.cashbox.CashboxViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventCreateModel
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventListModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionEditModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionCreateModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionViewModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel
import com.github.onotoliy.opposite.viewmodel.users.UserEditModel
import com.github.onotoliy.opposite.viewmodel.users.UserCreateModel
import com.github.onotoliy.opposite.viewmodel.users.UserViewModel
import com.github.onotoliy.opposite.viewmodel.users.UserListModel
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
    factory { (id: String) -> EventTransactionListModel(get(), id) }
    factory { (id: String) -> EventUserListModel(get(), id) }
    single { EventListModel(get()) }
    single { EventCreateModel(get()) }

    factory { (id: String) -> TransactionViewModel(get(), get(), id) }
    factory { (id: String) -> TransactionEditModel(get(), id) }
    single { TransactionListModel(get()) }
    single { TransactionCreateModel(get()) }

    factory { (id: String) -> UserViewModel(get(), get(), id) }
    factory { (id: String) -> UserEditModel(get(), id) }
    factory { (id: String) -> UserTransactionListModel(get(), id) }
    factory { (id: String) -> UserEventListModel(get(), id) }
    single { UserListModel(get()) }
    single { UserCreateModel(get()) }

    single { CashboxViewModel(get()) }
}