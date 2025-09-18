package com.github.onotoliy.opposite.ui.users.models

import com.github.onotoliy.opposite.repositories.DepositeRepository
import com.github.onotoliy.opposite.repositories.FileRepository
import com.github.onotoliy.opposite.repositories.newDeposit
import com.github.onotoliy.opposite.treasure.model.Deposit
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import opposite_treasure_kmp.app.generated.resources.Res
import opposite_treasure_kmp.app.generated.resources._29a98ff153997867b0760fd8d812f12478e180a3
import org.jetbrains.compose.resources.DrawableResource

class UserViewModel(
    private val users: DepositeRepository,
    private val files: FileRepository,
    private val uuid: String
) : AbstractViewModel<Deposit>(){
    private val _logo = MutableStateFlow<DrawableResource>(Res.drawable._29a98ff153997867b0760fd8d812f12478e180a3)

    val logo: StateFlow<DrawableResource> = _logo

    override suspend fun get(): Deposit = users.get(uuid)

    override suspend fun loadAdditionalValues() {
        _logo.value = files.download()
    }

    override val defaultValue: Deposit
        get() = newDeposit()

}
