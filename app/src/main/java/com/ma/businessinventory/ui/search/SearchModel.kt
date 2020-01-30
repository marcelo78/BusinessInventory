package com.ma.businessinventory.ui.search

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ma.businessinventory.MyApplication

class SearchModel(private var presenter: Search.Presenter) : Search.Model {

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