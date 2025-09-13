package com.github.onotoliy.opposite.ui.components.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.components.EditFloatingActionButton
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import com.github.onotoliy.opposite.ui.navigation.Screen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import kotlin.time.ExperimentalTime

@Composable
expect fun UserInformationView(user: User, logo: DrawableResource, onSelect: (Screen) -> Unit)

@OptIn(ExperimentalTime::class)
@Composable
fun UserInformationMobileView(user: User, logo: DrawableResource, onSelect: (Screen) -> Unit) {
    LocalMobileScafoldState.current.topBar = { Text(user.name) }
    LocalMobileScafoldState.current.floatingActionButton = {
        EditFloatingActionButton {
            onSelect(
                Screen.UserEditScreen(user.uuid)
            )
        }
    }

    Column(
        modifier = Modifier.padding(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        LabelledText("ФИО", user.name)
        LabelledText("Депозит", user.deposit)
        LabelledText("Номер телефона", user.login)
        LabelledText("День рождения", user.birthday)
        LabelledText("День вступления", user.joiningDate)
        LabelledText("Должность", user.position)

        ElevatedCard(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Image(
                painter = painterResource(logo),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            )
        }
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun UserInformationWebView(user: User, logo: DrawableResource) {
    Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(logo),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            modifier = Modifier.size(300.dp)
        )

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LabelledText("Название", user.name)
            LabelledText("Депозит", user.deposit)
            LabelledText("Номер телефона", user.login)
            LabelledText("День рождения", user.birthday)

            Button(
                onClick = { /* обработка */ },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Сохранить")
            }
        }
    }
}
