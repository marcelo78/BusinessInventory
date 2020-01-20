package com.ma.businessinventory.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ma.businessinventory.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAll(): List<ProductEntity>

    @Query("SELECT * FROM product WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<ProductEntity>

    @Query("SELECT * FROM product WHERE name_inventory LIKE :name LIMIT 1")
    fun findByName(name: String): ProductEntity

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Delete
    fun delete(product: ProductEntity)
}