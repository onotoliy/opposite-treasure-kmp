package com.github.onotoliy.opposite.ui.components.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.navigation.goto

class MobileScafoldState {
    var titleTopBar by mutableStateOf<(@Composable () -> Unit)?>(null)
    var actionsTopBar by mutableStateOf<(@Composable () -> Unit)?>(null)
    var floatingActionButton by mutableStateOf<(@Composable () -> Unit)?>(null)
}

val LocalMobileScafoldState = staticCompositionLocalOf<MobileScafoldState> {
    error("No TopBarState provided")
}

val LocalNavHostController = staticCompositionLocalOf<NavHostController> {
    error("No TopBarState provided")
}

@Composable
fun Modifier.goto(screen: Screen): Modifier {
    val nav = LocalNavHostController.current

    return clickable { nav.goto(screen) }
}
