package com.ma.businessinventory.db.interactor

import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.repository.IProductRepository
import io.reactivex.Observable

class ProductInteractor(private val iProductRepository: IProductRepository) : IProductInteractor {

    override fun getAll(): Observable<MutableList<ProductEntity>> {
        return iProductRepository.getAll()
    }

    override fun loadAllByIds(ids: Long): Observable<ProductEntity> {
        return iProductRepository.loadAllByIds(ids)
    }

    override fun findByName(name: String): Observable<ProductEntity> {
        return iProductRepository.findByName(name)
    }

    override fun insertAll(products: MutableList<ProductEntity>) {
        iProductRepository.insertAll(products)
    }

    override fun insert(product: ProductEntity) {
        iProductRepository.insert(product)
    }

    override fun delete(product: ProductEntity) {
        iProductRepository.delete(product)
    }

    override fun deleteAll() {
        iProductRepository.deleteAll()
    }

    override fun update(product: ProductEntity) {
        iProductRepository.update(product)
    }

}
