package com.ma.businessinventory.ui.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.interactor.IProductInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPresenter(private val iProductInteractor: IProductInteractor) : Search.Presenter,
    ViewModel() {

    private val allProducts: MutableLiveData<MutableList<ProductEntity>> = MutableLiveData()

    init {
        loadProducts()
    }

    override fun getItems(): LiveData<MutableList<ProductEntity>> {
        return allProducts
    }

    @SuppressLint("CheckResult")
    fun loadProducts() {
        iProductInteractor.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { products -> allProducts.postValue(products) },
                { Log.d("RxJava", "Error getting info from interactor into presenter") }
            )
    }

}
