package com.github.onotoliy.opposite.ui.users

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.components.ApplicationScaffold

@Composable
fun UserEditScreen() = UserScreen()

@Composable
private fun UserScreen() {
    ApplicationScaffold(
        onSelect = {  }
    ) {
        Text("UserScreen")
    }
}
