package com.github.onotoliy.opposite.ui.users.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.github.onotoliy.opposite.ui.components.scaffold.ApplicationScaffold
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto
import com.github.onotoliy.opposite.ui.users.models.UserCreateModel
import com.github.onotoliy.opposite.ui.users.views.UserCreateView
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserCreateScreen(model: UserCreateModel = koinViewModel(), nav: NavController) {
    ApplicationScaffold(onSelect = nav::goto) {
        UserCreateView(model, nav::goto)
    }
}
