package com.jescoding.simpleexpense.presentation.screens.add_screen.components

import com.jescoding.simpleexpense.R
import android.content.res.Configuration
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.jescoding.simpleexpense.presentation.ui.theme.SimpleExpenseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
        ),
        title = {
            Text(
                text = "Add Expense",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onTertiary,
            )
        },
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onTertiary
                )
            }
        }
    )
}


@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
fun TopBarPreviewDark() {
    SimpleExpenseTheme {
        TopBar()
    }

}

@Composable
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun TopBarPreviewLight() {
    SimpleExpenseTheme{
        TopBar()
    }
}
