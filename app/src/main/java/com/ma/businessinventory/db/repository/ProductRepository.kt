package com.ma.businessinventory.db.repository

import android.annotation.SuppressLint
import android.util.Log
import com.ma.businessinventory.db.dao.ProductDao
import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.entities.SummaryEntity
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ProductRepository(private val productDao: ProductDao) : IProductRepository {

    override fun getAll(): Observable<MutableList<ProductEntity>> = productDao.getAll()

    override fun loadAllByIds(ids: Long): Observable<ProductEntity> = productDao.loadAllByIds(ids)

    override fun findByName(name: String): Observable<ProductEntity> = productDao.findByName(name)

    @SuppressLint("CheckResult")
    override fun insertAll(products: MutableList<ProductEntity>) {
        productDao.insertAll(products)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                run { Log.d("RxJava", "Insert Success") }
            }
    }

    @SuppressLint("CheckResult")
    override fun insert(product: ProductEntity): Disposable {
        return productDao.insert(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                run { Log.d("RxJava", "Insert Success") }
            }
    }

    @SuppressLint("CheckResult")
    override fun delete(product: ProductEntity): Disposable {
        return productDao.delete(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                run { Log.d("RxJava", "Delete Success") }
            }
    }

    @SuppressLint("CheckResult")
    override fun deleteAll() {
        Completable.fromAction { productDao.deleteAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                run { Log.d("RxJava", "Delete all Success") }
            }
    }

    @SuppressLint("CheckResult")
    override fun update(product: ProductEntity): Disposable {
        return productDao.update(product)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                run { Log.d("RxJava", "Update Success") }
            }
    }

    override fun allSummary(): Observable<MutableList<SummaryEntity>> = productDao.allSummary()

}
