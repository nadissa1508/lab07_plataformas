package com.example.lab7.ui.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7.ui.model.Meal
import com.example.lab7.ui.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealsViewModel(private val repository: MealRepository, category: String) : ViewModel() {
    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals

    init {
        fetchMealsByCategory(category)
    }

    private fun fetchMealsByCategory(category: String) {
        viewModelScope.launch {
            _meals.value = repository.getMealsByCategory(category).meals
        }
    }
}