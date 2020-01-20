package com.ma.businessinventory.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entity.ProductEntity

//@Database(entities = [ProductEntity::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun productDao(): ProductDao
//}

@Database(entities = arrayOf(ProductEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "businessinventory"
                ).build()
            }

            return INSTANCE as AppDatabase
        }
    }
}