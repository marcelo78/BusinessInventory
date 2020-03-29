package com.ma.businessinventory.ui.detailitem

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.interactor.IProductInteractor
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class ItemDetailPresenter(private val iProductInteractor: IProductInteractor) :
    IItemDetail.Presenter, ViewModel() {

    override fun getItem(idItem: Long): Observable<ProductEntity> {
        Log.d("ItemDetailPresenter", "Entro a a traer data")
        return iProductInteractor.loadAllByIds(idItem)
    }

    override fun deleteItem(product: ProductEntity): Disposable {
        return iProductInteractor.delete(product)
    }

}
