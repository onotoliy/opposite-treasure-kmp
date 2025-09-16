package com.github.onotoliy.opposite.viewmodel.users

import com.github.onotoliy.opposite.repositories.IDepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel

class UserCreateModel(
    private val repository: IDepositeRepository
) : AbstractCreateModel<Deposit>() {

    override suspend fun create(value: Deposit): Deposit = repository.create(value)

}
