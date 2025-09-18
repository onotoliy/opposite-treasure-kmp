package com.github.onotoliy.opposite

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import com.github.onotoliy.opposite.di.adapterModel
import com.github.onotoliy.opposite.di.networkModule
import com.github.onotoliy.opposite.di.repositoryModule
import com.github.onotoliy.opposite.di.viewModelModule
import com.github.onotoliy.opposite.ui.navigation.WebWindowNavigation
import org.koin.compose.KoinApplication


fun main() = singleWindowApplication (
    title = "Chat",
    state = WindowState(size = DpSize(500.dp, 800.dp))
) {
    KoinApplication(application = {
        modules(networkModule, repositoryModule, viewModelModule, adapterModel)
    }) {
        WebWindowNavigation() {
            it.navigatorProvider
        }
    }
}