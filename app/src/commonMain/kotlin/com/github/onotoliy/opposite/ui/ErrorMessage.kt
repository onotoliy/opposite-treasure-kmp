package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ErrorMessage(message: String) {
    OutlinedTextField(
        value = message,
        onValueChange = {

        },
        label = {
            Row {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "Ошибка",
                    tint = Color.Red
                )
                Text("Ошибка")
            }

        },
        readOnly = true,
        isError = true,
        modifier = Modifier.fillMaxWidth()
    )
}
