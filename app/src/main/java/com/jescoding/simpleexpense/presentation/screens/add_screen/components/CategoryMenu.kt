package com.jescoding.simpleexpense.presentation.screens.add_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
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

@Composable
fun CategoriesDropdown(
    selectedIndex: Int,
    items: List<String>,
    onClick: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
        .background(TextFieldDefaults.colors().unfocusedContainerColor)
        .fillMaxWidth()
        .height(48.dp)
        .clickable {
            expanded = true
        }
    ) {
        Text(
            text = items[selectedIndex],
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(start = 16.dp, end = 16.dp)
        )

        Image(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(60.dp)
                .padding(end = 8.dp),
            painter = (painterResource(id = R.drawable.baseline_arrow_drop_down_24)),
            contentDescription = "Categories",
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondaryContainer)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        onClick(index)
                        expanded = false
                    }
                )
            }
        }

    }

}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
fun CategoriesDropdownPreviewLight() {
    SimpleExpenseTheme {
        CategoriesDropdown(
            0,
            listOf("Food", "Transport", "Entertainment"),
            {}
        )
    }

}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun CategoriesDropdownPreviewDark() {
    SimpleExpenseTheme {
        CategoriesDropdown(0,
            listOf("Food", "Transport", "Entertainment"),
            {}
        )
    }
}