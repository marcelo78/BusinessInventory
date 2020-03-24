package com.ma.businessinventory.db.dao

import androidx.room.*
import com.ma.businessinventory.db.entities.ProductEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface ProductDao {

    @Query("SELECT * FROM product ORDER BY 2 ASC")
    fun getAll(): Observable<MutableList<ProductEntity>>

    @Query("SELECT * FROM product WHERE id=:ids")
    fun loadAllByIds(ids: Long): Observable<ProductEntity>

    @Query("SELECT * FROM product WHERE name_inventory LIKE :name LIMIT 1")
    fun findByName(name: String): Observable<ProductEntity>

    @Insert
    fun insertAll(products: MutableList<ProductEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductEntity): Completable

    @Delete
    fun delete(product: ProductEntity): Completable

    @Query("DELETE FROM product")
    fun deleteAll()

    @Update
    fun update(product: ProductEntity): Completable

//    @Query("SELECT SUM((bought_no - sold_no) * unid_buy_price_us) data1, SUM(CASE WHEN bought_no = sold_no THEN bought_no ELSE (bought_no - sold_no) END) data2, SUM(CASE WHEN bought_no = sold_no THEN 0 ELSE (sold_no) END) data3, ROUND(SUM(total_profit_us), 2) data4, ROUND(SUM(total_cost_us), 2) data5, ROUND(SUM(sold_no * unid_sell_price_us), 2) data6 FROM product")
//    fun getSummary(): LiveData<List<SummaryEntity>>

}
