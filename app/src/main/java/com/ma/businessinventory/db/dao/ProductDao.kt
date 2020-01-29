package com.ma.businessinventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ma.businessinventory.db.entity.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM product ORDER BY 2 ASC")
    fun getAll(): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE id=:ids")
    fun loadAllByIds(ids: Long): LiveData<List<ProductEntity>>

    @Query("SELECT * FROM product WHERE name_inventory LIKE :name LIMIT 1")
    fun findByName(name: String): LiveData<ProductEntity>

    @Insert
    fun insertAll(vararg products: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntity)

    @Delete
    suspend fun delete(product: ProductEntity): Int

    @Query("DELETE FROM product")
    suspend fun deleteAll()

    @Update
    suspend fun update(product: ProductEntity): Int

}
