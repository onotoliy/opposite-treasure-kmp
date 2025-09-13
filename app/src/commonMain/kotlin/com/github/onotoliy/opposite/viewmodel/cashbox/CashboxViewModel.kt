package com.github.onotoliy.opposite.viewmodel.cashbox

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.ui.components.users.newUser
import com.github.onotoliy.opposite.viewmodel.AbstractViewModel
import com.github.onotoliy.opposite.viewmodel.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class CashboxViewModel(
    private val users: IUserRepository,
) : AbstractViewModel<User>() {
    override suspend fun get(): User = users.get("1")

    override suspend fun loadAdditionalValues() {
    }

    override val defaultValue: User
        get() = com.github.onotoliy.opposite.repositories.newUser(-1000)

}
