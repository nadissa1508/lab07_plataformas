package com.example.lab7.ui.mealdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7.ui.model.MealDetails
import com.example.lab7.ui.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MealDetailViewModel(private val repository: MealRepository) : ViewModel() {
    private val _mealDetails = MutableStateFlow<MealDetails?>(null)
    val mealDetails: StateFlow<MealDetails?> = _mealDetails

    fun fetchMealDetails(id: String) {
        viewModelScope.launch {
            _mealDetails.value = repository.getMealDetails(id).meals.firstOrNull()
        }
    }
}