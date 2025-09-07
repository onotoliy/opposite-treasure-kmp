package com.github.onotoliy.opposite.ui.cashbox

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.github.onotoliy.opposite.data.Option
import com.github.onotoliy.opposite.ui.CalendarField
import com.github.onotoliy.opposite.ui.SwaggestBox
import com.github.opposite.treasure.shared.EventCacheRepository
import kotlinx.coroutines.delay
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn( ExperimentalMaterial3Api::class, ExperimentalTime::class)
@Composable
fun CashboxScreen() {
    val s = "Длинный StakTrase ошибка\nДлинный StakTrase ошибка\nДлинный StakTrase ошибка\nДлинный StakTrase ошибка\n"


//    SwaggestBox(
//        label="События",
//        onSelected = {
//            println(it)
//        },
//        onQueryChanged = { q ->
//            println(q)
//            delay(1000)
//
//            if (q.isEmpty()) {
//                EventCacheRepository.EVENTS.map { Option(it.uuid, it.name) }
//            } else {
//                EventCacheRepository.EVENTS.map { Option(it.uuid, it.name) }
//                    .filter { it.name.contains(q) }
//            }
//        }
//    )

    var selectedDate by remember { mutableStateOf<Instant>(Clock.System.now()) }

    CalendarField(
        label = "Сдать до",
        value = selectedDate
    ) {
        selectedDate = it
    }
}