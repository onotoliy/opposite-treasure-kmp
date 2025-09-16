package com.github.onotoliy.opposite.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.datetime.Instant
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@Composable
fun LabelledText(
    label: String,
    text: Instant,
    onTextClick: (() -> Unit)? = null,
) = LabelledText(label, text.toString(), onTextClick)

@Composable
fun LabelledText(
    label: String,
    text: String,
    onTextClick: (() -> Unit)? = null,
) {
    Row {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(120.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .then(
                    if (onTextClick != null) Modifier.clickable { onTextClick() } else Modifier
                ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}