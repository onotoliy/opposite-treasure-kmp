package com.github.onotoliy.opposite.viewmodel.users

import androidx.lifecycle.ViewModel
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.repositories.IDebtRepository
import com.github.onotoliy.opposite.repositories.IFileRepository
import com.github.onotoliy.opposite.repositories.ITransactionRepository
import com.github.onotoliy.opposite.repositories.IUserRepository
import com.github.onotoliy.opposite.viewmodel.UiState
import com.github.opposite.treasure.shared.IEventRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

data class UserView(
    val user: User,
    val transactions: List<Transaction>,
    val debts: List<Event>,
    val logo: DrawableResource
)

open class UserViewModel(
    private val users: IUserRepository,
    private val files: IFileRepository,
    private val transactions: ITransactionRepository,
    private val debts: IDebtRepository,
    private val uuid: String
) : ViewModel() {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private val _state = MutableStateFlow<UiState<UserView>>(UiState.Loading)
    val state: StateFlow<UiState<UserView>> = _state

    fun load() {
        _state.value = UiState.Loading

        scope.launch {
            try {
                val view = UserView(
                    user = users.get(uuid),
                    logo = files.download(uuid),
                    transactions = transactions.getAll(eventID = uuid),
                    debts = debts.getDebts(userID = uuid)
                )

                _state.value = UiState.Success(view)
            } catch (e: Exception) {
                _state.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
