package com.example.lab7.ui.supermarket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab7.ui.repository.SupermarketRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupermarketViewModel(private val repository: SupermarketRepository) : ViewModel() {
    private val _items = MutableStateFlow<List<SupermarketItem>>(emptyList())
    val items: StateFlow<List<SupermarketItem>> = _items

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            repository.getAllItems().collect { fetchedItems ->
                _items.value = fetchedItems
            }
        }
    }

    fun addItem(item: SupermarketItem) {
        viewModelScope.launch {
            repository.addItem(item)
            fetchItems()
        }
    }
}
