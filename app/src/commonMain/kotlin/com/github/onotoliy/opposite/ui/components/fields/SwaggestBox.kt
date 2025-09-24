package com.github.onotoliy.opposite.ui.components.fields

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.github.onotoliy.opposite.treasure.model.Option
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun SwaggestBox(
    label: String,
    value: Option?,
    enabled: Boolean,
    onSelected: (Option) -> Unit,
    onQueryChanged: (String) -> List<Option>
) {
    var expanded by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf(value?.name ?: "") }
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
            enabled = enabled,
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
                    text = { Text(option.name) },
                    onClick = {
                        expanded = false

                        onSelected(option)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    value: Option,
    options: List<Option>,
    enabled: Boolean,
    onValueChange: (Option) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(value) }

    LaunchedEffect(value) {
        selectedOption = value
    }

    ExposedDropdownMenuBox(
        modifier = Modifier.clickable(onClick = { expanded = !expanded }),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
//        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = selectedOption.name,
                onValueChange = {},
                label = { Text(label) },
                readOnly = true,
                enabled = enabled,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable, enabled).fillMaxWidth()
            )

//            Box(modifier = Modifier.matchParentSize().clickable { expanded = true })
//        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.name) },
                    onClick = {
                        onValueChange(option)
                        selectedOption = option
                        expanded = false
                    }
                )
            }
        }
    }
}
