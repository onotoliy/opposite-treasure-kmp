package com.github.onotoliy.opposite.ui.components.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AddFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
    }
}

@Composable
fun SaveFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Save, contentDescription = null)
    }
}

@Composable
fun EditFloatingActionButton(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Row {
            Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null)
            Text("Отмена")
        }
    }
}


@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Row {
            Icon(imageVector = Icons.Outlined.Save, contentDescription = null)
            Text("Сохранить")
        }
    }
}

@Composable
fun DeleteButton(onClick: () -> Unit) {
    Button(onClick = onClick) {
        Row {
            Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
            Text("Удалить")
        }
    }
}