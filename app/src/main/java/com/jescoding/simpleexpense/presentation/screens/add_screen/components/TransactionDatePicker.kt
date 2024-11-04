package com.jescoding.simpleexpense.presentation.screens.add_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jescoding.simpleexpense.R
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDatePicker(
    selectedDate: String,
    onConfirmed: (DatePickerState) -> Unit = {}
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
            .background(TextFieldDefaults.colors().unfocusedContainerColor)
            .fillMaxWidth()
            .height(48.dp)
            .clickable {
                showBottomSheet = true
            }
    ) {
        Text(
            text = selectedDate,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )

        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(60.dp)
                .padding(end = 8.dp),
            painter = (painterResource(id = R.drawable.baseline_date_range_24)),
            contentDescription = "Transaction Date",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer)
        )

        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                content = {
                    DatePickerContent(
                        onDateSelected = {
                            showBottomSheet = false
                            onConfirmed(it)
                        },
                        onClose = {
                            showBottomSheet = false
                        }
                    )
                },
                onDismissRequest = {
                    showBottomSheet = false
                }
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerContent(
    onDateSelected: (DatePickerState) -> Unit,
    onClose: () -> Unit
) {
    val state = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    Column {
        DatePicker(state = state, showModeToggle = true)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            TextButton(onClick = onClose) { Text("Cancel") }

            Spacer(modifier = Modifier.width(8.dp))

            TextButton(onClick = {
                onDateSelected(state)
                onClose()
            }) {
                Text("Confirm")
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun DatePickerBottomSheetPreviewLight() {
    SimpleExpenseTheme {
        TransactionDatePicker(
            selectedDate = "12/24/2024",
            onConfirmed = {}
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DatePickerBottomSheetPreviewDark() {
    SimpleExpenseTheme {
        TransactionDatePicker(
            selectedDate = "12/24/2024",
            onConfirmed = {}
        )
    }
}