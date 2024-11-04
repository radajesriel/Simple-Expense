package com.jescoding.simpleexpense.data.category

interface CategoryRepository {
    fun getCategories() : List<String>
}