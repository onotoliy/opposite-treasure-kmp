package com.github.onotoliy.opposite.ui.cashbox.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel

open class CashboxViewModel(
    private val users: DepositeRepository,
) : AbstractViewModel<Deposit>() {

    override suspend fun get(): Deposit = users.me()

    override suspend fun loadAdditionalValues() {

    }

    override val defaultValue: Deposit
        get() = newDeposit()

}
