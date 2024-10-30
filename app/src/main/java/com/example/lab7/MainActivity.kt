package com.example.lab7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab7.ui.Networking.RetrofitService
import com.example.lab7.ui.categories.CategoriesScreen
import com.example.lab7.ui.categories.CategoriesViewModel
import com.example.lab7.ui.mealdetail.MealDetailScreen
import com.example.lab7.ui.mealdetail.MealDetailViewModel
import com.example.lab7.ui.meals.MealsScreen
import com.example.lab7.ui.meals.MealsViewModel
import com.example.lab7.ui.repository.MealRepository
import com.example.lab7.ui.database.AppDatabase
import com.example.lab7.ui.supermarket.SupermarketViewModel
import com.example.lab7.ui.supermarket.SupermarketListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getInstance(this)
        val categoryDao = database.categoryDao()

        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = "categories") {
                composable("categories") {
                    val categoriesViewModel = CategoriesViewModel(MealRepository(RetrofitService.apiService, categoryDao))
                    CategoriesScreen(categoriesViewModel) { category ->
                        navController.navigate("meals/$category")
                    }
                }
                composable("meals/{category}") { backStackEntry ->
                    val category = backStackEntry.arguments?.getString("category") ?: "Seafood"
                    val mealsViewModel = MealsViewModel(MealRepository(RetrofitService.apiService, categoryDao), category)
                    MealsScreen(mealsViewModel) { mealId ->
                        navController.navigate("mealDetail/$mealId")
                    }
                }
                composable("mealDetail/{mealId}") { backStackEntry ->
                    val mealId = backStackEntry.arguments?.getString("mealId") ?: ""
                    val mealDetailViewModel = MealDetailViewModel(MealRepository(RetrofitService.apiService, categoryDao))
                    mealDetailViewModel.fetchMealDetails(mealId) // Fetch meal details
                    MealDetailScreen(mealDetailViewModel)
                }

                composable("supermarketList") {
                    val supermarketViewModel = SupermarketViewModel(SupermarketRepository(AppDatabase.getInstance(this).supermarketItemDao()))
                    SupermarketScreen(supermarketViewModel)
                }
            }
        }
    }
}