package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.onotoliy.opposite.data.Option
import kotlinx.coroutines.delay

@ExperimentalMaterialApi
@Composable
fun SwaggestBox(
    label: String,
    onSelected: (Option) -> Unit,
    onQueryChanged: suspend (String) -> List<Option>
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }
    var values by remember { mutableStateOf<List<Option>>(emptyList()) }

    LaunchedEffect(Unit) {
        values = onQueryChanged("")
    }

    // throttle / debounce
    LaunchedEffect(query) {
        delay(500)
        values = onQueryChanged(query)
        expanded = true
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = {
                query = it
            },
            label = { Text(label) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            values.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        expanded = false

                        onSelected(option)
                    }
                ) {
                    Text(option.name)
                }
            }
        }
    }
}