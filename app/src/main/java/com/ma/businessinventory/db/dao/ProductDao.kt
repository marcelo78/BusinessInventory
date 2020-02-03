package com.ma.businessinventory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ma.businessinventory.db.entity.ProductEntity
import com.ma.businessinventory.db.entity.SummaryEntity

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

    @Query("SELECT SUM((bought_no - sold_no) * unid_buy_price_us) data1, SUM(CASE WHEN bought_no = sold_no THEN bought_no ELSE (bought_no - sold_no) END) data2, SUM(CASE WHEN bought_no = sold_no THEN 0 ELSE (sold_no) END) data3, ROUND(SUM(total_profit_us), 2) data4, ROUND(SUM(total_cost_us), 2) data5, ROUND(SUM(sold_no * unid_sell_price_us), 2) data6 FROM product")
    fun getSummary(): LiveData<List<SummaryEntity>>

}
