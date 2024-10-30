package com.example.lab7.ui.repository

import com.example.lab7.ui.supermarket.SupermarketItem
import com.example.lab7.ui.supermarket.SupermarketItemDao
import kotlinx.coroutines.flow.Flow

class SupermarketRepository(private val supermarketItemDao: SupermarketItemDao) {

    // Método para obtener todos los elementos de la base de datos
    fun getAllItems(): Flow<List<SupermarketItem>> {
        return supermarketItemDao.getAllItems()
    }

    // Método para agregar un nuevo elemento a la base de datos
    suspend fun addItem(item: SupermarketItem) {
        supermarketItemDao.insertItem(item)
    }

    // Método para eliminar un elemento de la base de datos
    suspend fun deleteItem(item: SupermarketItem) {
        supermarketItemDao.deleteItem(item)
    }
}