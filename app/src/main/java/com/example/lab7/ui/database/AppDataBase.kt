package com.example.lab7.ui.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.lab7.ui.database.CategoriesDB.CategoryDao
import com.example.lab7.ui.database.CategoriesDB.CategoryEntity
import com.example.lab7.ui.supermarket.SupermarketItem
import com.example.lab7.ui.supermarket.SupermarketItemDao

@Database(entities = [CategoryEntity::class, SupermarketItem::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun supermarketItemDao(): SupermarketItemDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration() // Permite la destrucción de la base de datos
                    .build()
                    .also { instance = it }
            }
        }

        /*val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Aquí, coloca la lógica de migración que necesitas
                // Por ejemplo, si agregaste una nueva columna a SupermarketItem:
                database.execSQL("ALTER TABLE supermarket_item ADD COLUMN new_column_name INTEGER DEFAULT 0 NOT NULL")
            }
        }*/
    }
}


/*
@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }
    }

    abstract fun supermarketItemDao(): SupermarketItemDao
}*/
