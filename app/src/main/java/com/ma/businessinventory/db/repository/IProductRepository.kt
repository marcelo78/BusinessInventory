package com.ma.businessinventory.db.repository

import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.entities.SummaryEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface IProductRepository {

    fun getAll(): Observable<MutableList<ProductEntity>>

    fun loadAllByIds(ids: Long): Observable<ProductEntity>

    fun findByName(name: String): Observable<ProductEntity>

    fun insertAll(products: MutableList<ProductEntity>)

    fun insert(product: ProductEntity): Disposable

    fun delete(product: ProductEntity): Disposable

    fun deleteAll()

    fun update(product: ProductEntity): Disposable

    fun allSummary(): Observable<MutableList<SummaryEntity>>

}
