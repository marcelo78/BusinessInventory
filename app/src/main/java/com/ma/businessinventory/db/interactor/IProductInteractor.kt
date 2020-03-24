package com.ma.businessinventory.db.interactor

import com.ma.businessinventory.db.entities.ProductEntity
import io.reactivex.Observable

interface IProductInteractor {

    fun getAll(): Observable<MutableList<ProductEntity>>

    fun loadAllByIds(ids: Long): Observable<ProductEntity>

    fun findByName(name: String): Observable<ProductEntity>

    fun insertAll(products: MutableList<ProductEntity>)

    fun insert(product: ProductEntity)

    fun delete(product: ProductEntity)

    fun deleteAll()

    fun update(product: ProductEntity)

}
