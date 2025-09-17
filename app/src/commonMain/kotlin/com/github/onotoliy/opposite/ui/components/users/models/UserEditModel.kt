package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractEditModel

class UserEditModel(
    private val repository: DepositeRepository,
    private val uuid: String
): AbstractEditModel<Deposit>(uuid) {

    override suspend fun get(uuid: String): Deposit = repository.get(uuid)

    override suspend fun update(value: Deposit): Deposit = repository.update(value)

    override val defaultValue: Deposit
        get() = newDeposit()

}
