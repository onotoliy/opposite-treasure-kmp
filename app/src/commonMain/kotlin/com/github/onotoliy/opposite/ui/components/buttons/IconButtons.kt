package com.github.onotoliy.opposite.ui.components.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.PlusOne
import androidx.compose.material.icons.outlined.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

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
    Button(shape = RectangleShape, onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Cancel, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Отмена")
    }
}

@Composable
fun EditButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(modifier = modifier, shape = RectangleShape, onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Изменить")
    }
}

@Composable
fun SaveButton(onClick: () -> Unit) {
    Button(shape = RectangleShape, onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Save, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Сохранить")
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    Button(shape = RectangleShape, onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Создать")
    }
}


@Composable
fun DeleteButton(onClick: () -> Unit) {
    Button(
        shape = RectangleShape,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)
    ) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        Text("Удалить")
    }
}

@Composable
fun DeleteIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(imageVector = Icons.Outlined.Delete, contentDescription = null)
    }
}