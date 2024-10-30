package com.example.lab7.ui.repository

import com.example.lab7.ui.Networking.MealApiService
import com.example.lab7.ui.database.CategoriesDB.CategoryDao
import com.example.lab7.ui.database.CategoriesDB.CategoryEntity
import com.example.lab7.ui.model.Category

class MealRepository(private val apiService: MealApiService, private val categoryDao: CategoryDao) {
    suspend fun getCategories(): List<Category> {
        var categories = categoryDao.getCategories().map {
            Category(it.idCategory, it.strCategory, it.strCategoryThumb)
        }
        if (categories.isEmpty()) {
            categories = apiService.getCategories().categories
            categoryDao.insertCategories(categories.map {
                CategoryEntity(it.idCategory, it.strCategory, it.strCategoryThumb)
            })
        }
        return categories
    }
    suspend fun getMealsByCategory(category: String) = apiService.getMealsByCategory(category)
    suspend fun getMealDetails(id: String) = apiService.getMealDetails(id)
}