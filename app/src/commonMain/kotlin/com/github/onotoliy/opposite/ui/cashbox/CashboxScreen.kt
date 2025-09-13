package com.github.onotoliy.opposite.ui.cashbox

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.UiStateScreen
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.users.UserViewScreen
import com.github.onotoliy.opposite.viewmodel.cashbox.CashboxViewModel
import org.koin.compose.viewmodel.koinViewModel
import kotlin.time.ExperimentalTime

@OptIn( ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun CashboxScreen(model: CashboxViewModel = koinViewModel(), onSelect: (Screen) -> Unit) {
    val state by model.state.collectAsState()
    UiStateScreen<User>(state, load = model::load) { data ->
        UserViewScreen(data.uuid, onSelect = onSelect)
    }
}