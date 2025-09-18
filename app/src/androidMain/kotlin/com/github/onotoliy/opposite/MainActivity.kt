package com.github.onotoliy.opposite

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.github.onotoliy.opposite.di.adapterModel
import com.github.onotoliy.opposite.di.networkModule
import com.github.onotoliy.opposite.di.repositoryModule
import com.github.onotoliy.opposite.di.viewModelModule
import com.github.onotoliy.opposite.ui.navigation.WebWindowNavigation
import org.koin.compose.KoinApplication

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(application = {
                modules(networkModule, repositoryModule, viewModelModule, adapterModel)
            }) {
                WebWindowNavigation () {

                }
            }
        }
    }
}
