package com.ma.businessinventory.db

import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entity.ProductEntity

class ProductRepository(private val productDao: ProductDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allProducts: LiveData<List<ProductEntity>> = productDao.getAll()

    suspend fun insert(productEntity: ProductEntity) {
        productDao.insert(productEntity)
    }

    suspend fun update(productEntity: ProductEntity):Int {
        return productDao.update(productEntity)
    }

    fun getItem(ids: Long): LiveData<List<ProductEntity>> {
        return productDao.loadAllByIds(ids)
    }
}
