package com.example.lab7.ui.model

data class MealCategoriesResponse(val categories: List<Category>)
data class Category(val idCategory: String, val strCategory: String, val strCategoryThumb: String)

data class MealsResponse(val meals: List<Meal>)
data class Meal(val idMeal: String, val strMeal: String, val strMealThumb: String)

data class MealDetailsResponse(val meals: List<MealDetails>)
data class MealDetails(val idMeal: String, val strMeal: String, val strInstructions: String, val strMealThumb: String)