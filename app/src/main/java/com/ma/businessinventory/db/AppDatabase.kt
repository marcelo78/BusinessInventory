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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [ProductEntity::class], version = 1, exportSchema = false)
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
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.productDao())
                    }
                }
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
                    0, "Hello1", "", "", "", "",
                    "", 0.0, 0.0, 0, 0,
                    0.0, 0, 0, ""
                )
            productDao.insert(product)
            Log.d("AppDatabase", "The products were added")
            product =
                ProductEntity(
                    1, "Hello2", "", "", "", "",
                    "", 0.0, 0.0, 0, 0,
                    0.0, 0, 0, ""
                )
            productDao.insert(product)
            Log.d("AppDatabase", "The products were added")
        }
    }
}