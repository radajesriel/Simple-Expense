package com.jescoding.simpleexpense.presentation.screens.add_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme

enum class InputType {
    AMOUNT, DESCRIPTION
}

@Composable
fun InputField(
    inputType: InputType,
    modifier: Modifier,
    colors: TextFieldColors,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    prefix: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    validation: (String) -> Boolean
) {
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(value) {
        isError = !validation(value)
    }

    TextField(
        modifier = modifier,
        isError = isError,
        colors = colors,
        value = value,
        onValueChange = onValueChange,
        prefix = prefix,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )

    if (isError) {
        val message = when (inputType) {
            InputType.AMOUNT -> "Input must not be zero"
            InputType.DESCRIPTION -> "Input must be at least 3 characters long"
        }

        Text(
            text = message,
            color = Color.Red,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun InputFieldPreviewDark() {
    SimpleExpenseTheme {
        InputField(
            inputType = InputType.AMOUNT,
            modifier = Modifier,
            colors = TextFieldDefaults.colors(),
            value = "",
            onValueChange = {},
            validation = ::validate
        )
    }

}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun InputFieldPreviewLight() {
    SimpleExpenseTheme {
        InputField(
            inputType = InputType.AMOUNT,
            modifier = Modifier,
            colors = TextFieldDefaults.colors(),
            value = "",
            onValueChange = {},
            validation = ::validate
        )
    }
}

private fun validate(value: String): Boolean {
    return false
}