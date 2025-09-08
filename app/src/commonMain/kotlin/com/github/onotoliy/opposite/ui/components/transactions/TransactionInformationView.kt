package com.github.onotoliy.opposite.ui.components.transactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.Event
import com.github.onotoliy.opposite.data.Transaction
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.components.transactions.view.TransactionInformationWebView
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
expect fun TransactionInformationView(transaction: Transaction, onSelect: (Screen) -> Unit)
