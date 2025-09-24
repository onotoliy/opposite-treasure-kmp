package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractCreateModel

class UserCreateModel(
    private val repository: DepositeRepository
) : AbstractCreateModel<Deposit>() {

    override suspend fun create(value: Deposit): Deposit = repository.create(value)

}
