package com.jescoding.simpleexpense.data.category

class DefaultCategoryRepository : CategoryRepository {
    override fun getCategories(): List<String> {
        return listOf<String>("Transportation", "Food", "Allowance")
    }
}