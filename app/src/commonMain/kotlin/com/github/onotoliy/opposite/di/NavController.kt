package com.github.onotoliy.opposite.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}