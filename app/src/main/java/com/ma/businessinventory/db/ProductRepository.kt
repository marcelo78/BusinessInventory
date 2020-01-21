package com.ma.businessinventory.db

import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entity.ProductEntity

class ProductRepository(private val productDao: ProductDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<ProductEntity>> = productDao.getAll()

    suspend fun insert(word: ProductEntity) {
        productDao.insert(word)
    }
}