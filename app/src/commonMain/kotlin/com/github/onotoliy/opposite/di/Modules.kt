package com.github.onotoliy.opposite.di

import com.github.onotoliy.opposite.repositories.CashboxRepository
import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.EventRepository
import com.github.onotoliy.opposite.repositories.FileRepository
import com.github.onotoliy.opposite.repositories.TransactionRepository
import com.github.onotoliy.opposite.treasure.api.CashboxResourceApi
import com.github.onotoliy.opposite.treasure.api.DepositResourceApi
import com.github.onotoliy.opposite.treasure.api.EventResourceApi
import com.github.onotoliy.opposite.treasure.api.TransactionResourceApi
import com.github.onotoliy.opposite.ui.cashbox.models.CashboxViewModel
import com.github.onotoliy.opposite.ui.events.models.EventCreateModel
import com.github.onotoliy.opposite.ui.events.models.EventEditModel
import com.github.onotoliy.opposite.ui.events.models.EventListModel
import com.github.onotoliy.opposite.ui.events.models.EventTransactionListModel
import com.github.onotoliy.opposite.ui.events.models.EventUserListModel
import com.github.onotoliy.opposite.ui.events.models.EventViewModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionCreateModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionEditModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionListModel
import com.github.onotoliy.opposite.ui.transactions.models.TransactionViewModel
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel
import com.github.onotoliy.opposite.ui.users.models.UserEditModel
import com.github.onotoliy.opposite.ui.users.models.UserEventListModel
import com.github.onotoliy.opposite.ui.users.models.UserListModel
import com.github.onotoliy.opposite.ui.users.models.UserTransactionListModel
import com.github.onotoliy.opposite.ui.users.models.UserViewModel
import org.koin.dsl.module

val networkModule = module {
    single { CashboxResourceApi("https://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { TransactionResourceApi("https://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { EventResourceApi("https://91.201.41.66/api/treasure/v1", getHttpClient()) }
    single { DepositResourceApi("https://91.201.41.66/api/treasure/v1", getHttpClient()) }
}

val repositoryModule = module {
    single { CashboxRepository(get()) }
    single { DepositeRepository(get()) }
    single { TransactionRepository(get()) }
    single { EventRepository(get()) }
    single { FileRepository() }
}

val viewModelModule = module {
    factory { (id: String) -> EventViewModel(get(), get(), id) }
    factory { (id: String) -> EventEditModel(get(), id) }
    factory { (id: String) -> EventTransactionListModel(get(), id) }
    factory { (id: String) -> EventUserListModel(get(), get(), id) }
    single { EventListModel(get()) }
    single { EventCreateModel(get()) }

    factory { (id: String) -> TransactionViewModel(get(), get(), id) }
    factory { (id: String) -> TransactionEditModel(get(), id) }
    single { TransactionListModel(get()) }
    single { TransactionCreateModel(get(), get(), get()) }

    factory { (id: String) -> UserViewModel(get(), get(), id) }
    factory { (id: String) -> UserEditModel(get(), id) }
    factory { (id: String) -> UserTransactionListModel(get(), id) }
    factory { (id: String) -> UserEventListModel(get(), get(),id) }
    single { UserListModel(get()) }
    single { UserCreateModel(get()) }

    single { CashboxViewModel(get()) }
}