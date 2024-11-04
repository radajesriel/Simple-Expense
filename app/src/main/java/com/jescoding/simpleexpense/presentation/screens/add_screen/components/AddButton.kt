package com.jescoding.simpleexpense.presentation.screens.add_screen.components

import android.content.res.Configuration
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jescoding.simpleexpense.R
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme

@Composable
fun AddButton(modifier: Modifier) {
    Button(
        modifier = modifier,
        onClick = {

        }
    ) {
        Text(text = stringResource(R.string.add_expense_btn_label))
    }
}


@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun AddButtonPreviewDark() {
    SimpleExpenseTheme {
        AddButton(modifier = Modifier)
    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun AddButtonPreviewLight() {
    SimpleExpenseTheme {
        AddButton(modifier = Modifier)
    }
}
