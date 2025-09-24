package com.github.onotoliy.opposite.ui.components.fields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import com.github.onotoliy.opposite.repositories.toPhonePrettyString

@Composable
fun PhoneField(
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
        onValueChange = {
            val cleaned = it
                .replace(" ", "")
                .replace("(", "")
                .replace(")", "")
            if (cleaned.matches(Regex("""^\d*([.]?\d*)$"""))) {
                if (cleaned.length <= 10) {
                    onValueChange(cleaned)
                }
            }
        },
        leadingIcon = { Text("+7") },
        visualTransformation = PhoneNumberTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        )
    )
}

class PhoneNumberTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        var out = text.text.toPhonePrettyString()

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int =
                when (offset) {
                    0 -> offset
                    in 1..3 -> offset + 1
                    in 4..6 -> offset + 3
                    else -> offset + 4
                }

            override fun transformedToOriginal(offset: Int): Int =
                when (offset) {
                    0 -> offset
                    in 1..5 -> offset - 1
                    in 6..10 -> offset - 3
                    else -> offset - 4
                }
        }
        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}
