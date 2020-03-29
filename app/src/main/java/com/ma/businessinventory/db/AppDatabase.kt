package com.ma.businessinventory.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entities.ProductEntity

@Database(entities = [ProductEntity::class], version = AppDatabase.VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val VERSION = 1
        const val DATABASE_NAME = "businessinventory_database"
    }

    abstract fun productDao(): ProductDao

}
