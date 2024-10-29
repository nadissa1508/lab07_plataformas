package com.example.lab7.ui.Networking

import com.example.lab7.ui.model.MealCategoriesResponse
import com.example.lab7.ui.model.MealDetailsResponse
import com.example.lab7.ui.model.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApiService {
    @GET("categories.php")
    suspend fun getCategories(): MealCategoriesResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): MealDetailsResponse
}