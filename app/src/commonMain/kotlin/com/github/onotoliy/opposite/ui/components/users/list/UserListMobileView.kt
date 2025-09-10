package com.github.onotoliy.opposite.ui.components.users.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.onotoliy.opposite.data.User
import com.github.onotoliy.opposite.ui.navigation.Screen

@Composable
fun UserListMobileView(users: List<User>, onSelect: (Screen) -> Unit) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(users.size) {
            UserMobileItem(users[it], onSelect)
        }
    }
}

@Composable
private fun UserMobileItem(user: User, onSelect: (Screen) -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .clickable { onSelect(Screen.UserViewScreen(user.uuid)) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Outlined.People, contentDescription = null)

        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = user.name)

                Spacer(modifier = Modifier.height(4.dp))

                Text(text = user.deposit)
            }

            HorizontalDivider()
        }
    }

}