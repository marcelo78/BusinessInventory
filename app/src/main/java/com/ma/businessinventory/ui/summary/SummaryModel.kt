package com.ma.businessinventory.ui.summary

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ma.businessinventory.MyApplication

class SummaryModel(private var presenter: ISummary.Presenter) : ISummary.Model {

    override fun getSummary(activity: Activity) {

        val productViewModel = (activity.application as MyApplication).productViewModel

        productViewModel.allSummary.observe(activity as LifecycleOwner, Observer { products ->
            // Update the cached copy of the items in the adapter.
            Log.d("SummaryModel", "****************************")
            products.let {
                presenter.showItems(it)
            }
        })
    }

}
