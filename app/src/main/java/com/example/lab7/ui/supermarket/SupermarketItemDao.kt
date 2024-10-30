package com.example.lab7.ui.supermarket

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SupermarketItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SupermarketItem)

    @Query("SELECT * FROM supermarket_item")
    fun getAllItems(): Flow<List<SupermarketItem>>

    @Delete
    suspend fun deleteItem(item: SupermarketItem)
}