package com.github.onotoliy.opposite.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CurrencyRuble
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.github.onotoliy.opposite.repositories.toMoneyPrettyString

@Composable
fun MoneyField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        label = { Text(label) },
        value = value,
        onValueChange = { newText ->
            val cleaned = newText
                .replace(" ", "")
                .replace(",", ".")
            if (cleaned.matches(Regex("""^\d*([.]?\d*)$"""))) {
                onValueChange(cleaned)
            }
        },
        trailingIcon = { Icon(imageVector = Icons.Outlined.CurrencyRuble, contentDescription = null) },
        visualTransformation = ThousandsSeparatorTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    )
}

class ThousandsSeparatorTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val raw = text.text
        val out = raw.toMoneyPrettyString()

        // маппинг курсора
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val before = raw.split(".")[0].take(offset)
                val spaces = (before.length - 1) / 3
                return offset + spaces
            }

            override fun transformedToOriginal(offset: Int): Int {
                val withoutSpaces = out.take(offset).count { it != ' ' }
                return withoutSpaces
            }
        }

        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}
