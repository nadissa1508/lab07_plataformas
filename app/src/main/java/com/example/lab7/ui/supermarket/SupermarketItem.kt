package com.example.lab7.ui.supermarket

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supermarket_item")
data class SupermarketItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val itemName: String,
    val quantity: Int,
    val imagePath: String? = null
)