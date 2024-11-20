package com.jescoding.simpleexpense.domain.add_screen

data class ExpenseFormState(
    val description: String? = null,
    val amount: String? = null,
    val categoryIndex: Int = 0,
    val date: String? = null,
    val message: String? = null
)