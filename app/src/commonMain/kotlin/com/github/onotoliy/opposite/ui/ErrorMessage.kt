package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
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
