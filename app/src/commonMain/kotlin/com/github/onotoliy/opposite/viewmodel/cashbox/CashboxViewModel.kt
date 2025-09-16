package com.github.onotoliy.opposite.viewmodel.cashbox

import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel

open class CashboxViewModel(
    private val users: IDepositeRepository,
) : AbstractViewModel<Deposit>() {

    override suspend fun get(): Deposit = users.get("1")

    override suspend fun loadAdditionalValues() {
    }

    override val defaultValue: Deposit
        get() = newDeposit()

}
