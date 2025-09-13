package com.github.onotoliy.opposite.viewmodel.users

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IDebtRepository
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.repositories.newTransaction
import com.github.onotoliy.opposite.repositories.newUser
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import opposite_treasure_kmp.app.generated.resources.Res
import opposite_treasure_kmp.app.generated.resources._29a98ff153997867b0760fd8d812f12478e180a3
import org.jetbrains.compose.resources.DrawableResource

class UserViewModel(
    private val users: IUserRepository,
    private val files: IFileRepository,
    private val uuid: String
) : AbstractViewModel<User>(){
    private val _logo = MutableStateFlow<DrawableResource>(Res.drawable._29a98ff153997867b0760fd8d812f12478e180a3)

    val logo: StateFlow<DrawableResource> = _logo

    override suspend fun get(): User = users.get(uuid)

    override suspend fun loadAdditionalValues() {
        _logo.value = files.download(uuid)
    }

    override val defaultValue: User
        get() = newUser(-1000)

}