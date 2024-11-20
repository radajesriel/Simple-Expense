package com.jescoding.simpleexpense.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.toFormattedDate(): Date {
    val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return formatter.parse(this)
}

fun Long.toFormattedDate(): String {
    val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return formatter.format(this)
}

fun Date.toFormattedDate() : String {
    val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())
    return formatter.format(this)
}