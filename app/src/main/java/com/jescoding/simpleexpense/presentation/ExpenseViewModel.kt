package com.jescoding.simpleexpense.presentation

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jescoding.simpleexpense.data.category.DefaultCategoryRepository
import com.jescoding.simpleexpense.domain.add_screen.ExpenseFormState
import com.jescoding.simpleexpense.utils.toFormattedDate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ExpenseViewModel : ViewModel() {

    val categoryRepository = DefaultCategoryRepository()

    private val _expenseUiState = MutableStateFlow(ExpenseFormState())

    val amount: StateFlow<String> = _expenseUiState.map {
        it.amount.orEmpty()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")

    val description: StateFlow<String> = _expenseUiState.map {
        it.description.orEmpty()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")

    val date: StateFlow<String> = _expenseUiState.map {
        it.date ?: System.currentTimeMillis().toFormattedDate()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")

    val category: StateFlow<Int> = _expenseUiState.map {
        it.categoryIndex
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    fun updateAmount(amount: String) {
        if (amount.isDigitsOnly() && !amount.startsWith("0")) {
            _expenseUiState.value = _expenseUiState.value.copy(amount = amount)
        }
    }

    fun updateUiState(update: (ExpenseFormState) -> ExpenseFormState) {
        _expenseUiState.value = update(_expenseUiState.value)
    }

}