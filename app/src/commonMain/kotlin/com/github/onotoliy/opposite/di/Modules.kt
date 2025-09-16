package com.github.onotoliy.opposite.di

import com.github.onotoliy.opposite.repositories.ICashboxRepository
import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.treasure.api.CashboxResourceApi
import com.github.onotoliy.opposite.treasure.api.DepositResourceApi
import com.github.onotoliy.opposite.treasure.api.EventResourceApi
import com.github.onotoliy.opposite.treasure.api.TransactionResourceApi
import com.github.onotoliy.opposite.ui.components.events.models.EventTransactionListModel
import com.github.onotoliy.opposite.ui.components.events.models.EventUserListModel
import com.github.onotoliy.opposite.ui.components.users.models.UserEventListModel
import com.github.onotoliy.opposite.ui.components.users.models.UserTransactionListModel
import com.github.onotoliy.opposite.viewmodel.cashbox.CashboxViewModel
import com.github.onotoliy.opposite.viewmodel.events.EventCreateModel
import com.github.onotoliy.opposite.viewmodel.events.EventEditModel
import com.github.onotoliy.opposite.viewmodel.events.EventListModel
import com.github.onotoliy.opposite.viewmodel.events.EventViewModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionCreateModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionEditModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionListModel
import com.github.onotoliy.opposite.viewmodel.transactions.TransactionViewModel
import com.github.onotoliy.opposite.viewmodel.users.UserCreateModel
import com.github.onotoliy.opposite.viewmodel.users.UserEditModel
import com.github.onotoliy.opposite.viewmodel.users.UserListModel
import com.github.onotoliy.opposite.viewmodel.users.UserViewModel
import com.github.opposite.treasure.shared.IEventRepository
import org.koin.dsl.module

val networkModule = module {
    single { CashboxResourceApi("http://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { TransactionResourceApi("http://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { EventResourceApi("http://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { DepositResourceApi("http://91.201.41.66/api/treasure/v1", getHttpClient()) }
}

val repositoryModule = module {
    single { ICashboxRepository(get()) }
    single { IDepositeRepository(get()) }
    single { ITransactionRepository(get()) }
    single { IEventRepository(get()) }
    single { IFileRepository() }
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