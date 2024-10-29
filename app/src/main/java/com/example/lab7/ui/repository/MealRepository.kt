package com.example.lab7.ui.repository

import com.example.lab7.ui.Networking.MealApiService

class MealRepository(private val apiService: MealApiService) {
    suspend fun getCategories() = apiService.getCategories()
    suspend fun getMealsByCategory(category: String) = apiService.getMealsByCategory(category)
    suspend fun getMealDetails(id: String) = apiService.getMealDetails(id)
}