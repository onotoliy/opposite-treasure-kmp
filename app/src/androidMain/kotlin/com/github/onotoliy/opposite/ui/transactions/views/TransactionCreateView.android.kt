package com.github.onotoliy.opposite.ui.transactions.views

import androidx.compose.runtime.Composable
import com.github.onotoliy.opposite.ui.navigation.Screen
import com.github.onotoliy.opposite.ui.transactions.models.TransactionCreateModel

@Composable
actual fun TransactionCreateView(
    viewModel: TransactionCreateModel
) = TransactionCreateMobileView(viewModel)
