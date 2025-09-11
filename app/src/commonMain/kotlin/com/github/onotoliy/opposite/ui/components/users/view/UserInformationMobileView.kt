package com.github.onotoliy.opposite.ui.components.users.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.LabelledText
import com.github.onotoliy.opposite.ui.components.LocalMobileScafoldState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun UserInformationMobileView(user: User, logo: DrawableResource) {
    LocalMobileScafoldState.current.topBar = { Text(user.name) }
    LocalMobileScafoldState.current.floatingActionButton = { Icon(imageVector = Icons.Outlined.Edit, contentDescription = null) }

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
