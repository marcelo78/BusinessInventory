package com.ma.businessinventory.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businessinventory_database"
                )
                    .addCallback(WordDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
//                INSTANCE?.let { database ->
//                    scope.launch(Dispatchers.IO) {
//                        populateDatabase(database.productDao())
//                    }
//                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(productDao: ProductDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            productDao.deleteAll()
            Log.d("AppDatabase", "The products were deleted")

            var product =
                ProductEntity(
                    null, "Hello1", "Place1", "Description1", "Type1", "2020-01-22",
                    "", 10.0, 10.0, 5, 10,
                    15.0, 10, 10, ""
                )
            productDao.insert(product)
            Log.d("AppDatabase", "The products were added")
            product =
                ProductEntity(
                    null, "Hello2", "Place2", "Description2", "Type2", "2020-01-20",
                    "", 15.0, 11.0, 8, 4,
                    20.0, 11, 12, ""
                )
            productDao.insert(product)
            Log.d("AppDatabase", "The products were added")
        }
    }
}