package com.ma.businessinventory.ui.export

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ma.businessinventory.MyApplication

class ExportModel(private var presenter: IExport.Presenter) : IExport.Model {

    override fun getItems(activity: Activity) {
        val productViewModel = (activity.application as MyApplication).productViewModel

        productViewModel.allProducts.observe(activity as LifecycleOwner, Observer { products ->
            // Update the cached copy of the items in the adapter.
            Log.d("", "****************************")
            products.let {
                presenter.showItems(it)
            }
        })
    }

}
