package com.ma.businessinventory.ui.detailitem

import com.ma.businessinventory.db.entities.ProductEntity
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

interface IItemDetail {

    interface View {
        fun showResult()
    }

    interface Presenter {
        fun getItem(idItem: Long): Observable<ProductEntity>

        fun deleteItem(product: ProductEntity): Disposable
    }

}
