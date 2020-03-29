package com.ma.businessinventory.ui.summary

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ma.businessinventory.db.entities.SummaryEntity
import com.ma.businessinventory.db.interactor.IProductInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SummaryPresenter(private val iProductInteractor: IProductInteractor) : ISummary.Presenter,
    ViewModel() {

    private val allSummary: MutableLiveData<MutableList<SummaryEntity>> = MutableLiveData()

    init {
        loadSummary()
    }

    override fun getItems(): LiveData<MutableList<SummaryEntity>> {
        return allSummary
    }

    @SuppressLint("CheckResult")
    fun loadSummary() {
        iProductInteractor.allSummary()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { products -> allSummary.postValue(products) },
                { Log.d("RxJava", "Error getting info from interactor into presenter") }
            )
    }

}
