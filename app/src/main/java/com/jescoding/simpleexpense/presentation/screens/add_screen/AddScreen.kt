package com.jescoding.simpleexpense.presentation.screens.add_screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.AddButton
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.CategoriesDropdown
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.InputField
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.InputType
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.TopBar
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.TransactionDatePicker
import com.jescoding.simpleexpense.presentation.screens.add_screen.utils.CurrencyVisualTransformation
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme

private fun validateDescription(text: String): Boolean {
    return text.isNotEmpty() && text.length >= 3
}

private fun validateAmount(text: String): Boolean {
    return text.isNotEmpty() && text.toBigDecimalOrNull() != null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen() {
    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        topBar = {
            TopBar()
        },
        content = { it ->
            Box(modifier = Modifier.padding(it)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = "Total amount")

                    Spacer(Modifier.height(8.dp))

                    InputField(
                        inputType = InputType.AMOUNT,
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.colors(),
                        prefix = { Text("₱") },
                        value = "",
                        onValueChange = {},
                        keyboardType = KeyboardType.Number,
                        visualTransformation = CurrencyVisualTransformation(
                            fixedCursorAtTheEnd = true
                        ),
                        validation = ::validateAmount
                    )

                    Spacer(Modifier.height(8.dp))

                    Text("Category")

                    Spacer(Modifier.height(8.dp))

                    CategoriesDropdown(
                        selectedIndex = 0,
                        items = listOf("Food", "Transport", "Entertainment"),
                        onClick = {}
                    )

                    Spacer(Modifier.height(8.dp))

                    Text("Transaction Date")

                    Spacer(Modifier.height(8.dp))

                    TransactionDatePicker(
                        selectedDate = "12/24/2024",
                        onConfirmed = {}
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(text = "Description")

                    Spacer(Modifier.height(8.dp))

                    InputField(
                        inputType = InputType.DESCRIPTION,
                        colors = TextFieldDefaults.colors(),
                        modifier = Modifier
                            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                            .fillMaxWidth()
                            .height(200.dp),
                        value = "",
                        onValueChange = {

                        },
                        placeholder = {
                            Text(
                                text = "Add Description",
                                color = Color.DarkGray
                            )
                        },
                        validation = ::validateDescription
                    )
                }


                AddButton(
                    modifier = Modifier
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp)
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun AddScreenPreviewLight() {
    SimpleExpenseTheme {
        AddScreen()
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AddScrrenPreviewDark() {
    SimpleExpenseTheme {
        AddScreen()
    }
}