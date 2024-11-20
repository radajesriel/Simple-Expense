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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jescoding.simpleexpense.presentation.ExpenseViewModel
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.AddButton
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.CategoriesDropdown
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.InputField
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.InputType
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.TopBar
import com.jescoding.simpleexpense.presentation.screens.add_screen.components.TransactionDatePicker
import com.jescoding.simpleexpense.presentation.screens.add_screen.utils.CurrencyVisualTransformation
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme
import androidx.compose.runtime.getValue
import com.jescoding.simpleexpense.utils.toFormattedDate

private fun validateDescription(text: String): Boolean {
    return text.isNotEmpty() && text.length >= 3
}

private fun validateAmount(text: String): Boolean {
    return text.isNotEmpty() && text.toBigDecimalOrNull() != null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreen(viewModel: ExpenseViewModel = viewModel()) {

    val amount by viewModel.amount.collectAsState()
    val description by viewModel.description.collectAsState()
    val date by viewModel.date.collectAsState()
    val categoryIndex by viewModel.category.collectAsState()

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
                        .verticalScroll(rememberScrollState())
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
                        value = amount,
                        onValueChange = { amount ->
                            viewModel.updateAmount(amount)
                        },
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
                        selectedIndex = categoryIndex,
                        items = viewModel.categoryRepository.getCategories(),
                        onClick = { index ->
                            viewModel.updateUiState { currentState ->
                                currentState.copy(categoryIndex = index)
                            }
                        }
                    )

                    Spacer(Modifier.height(8.dp))

                    Text("Transaction Date")

                    Spacer(Modifier.height(8.dp))

                    TransactionDatePicker(
                        selectedDate = date,
                        onConfirmed = {
                            viewModel.updateUiState { currentState ->
                                currentState.copy(
                                    date = it.selectedDateMillis?.toFormattedDate()
                                )
                            }
                        }
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
                        value = description,
                        onValueChange = {
                            viewModel.updateUiState { currentState ->
                                currentState.copy(description = it)
                            }
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
                        .padding(16.dp)
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