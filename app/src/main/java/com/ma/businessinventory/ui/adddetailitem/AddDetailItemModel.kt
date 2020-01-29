package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ma.businessinventory.MyApplication
import com.ma.businessinventory.db.entity.ProductEntity

class AddDetailItemModel(private var presenter: AddDetailItem.Presenter) : AddDetailItem.Model {

    override fun insertItem(product: ProductEntity, activity: Activity) {
        (activity.application as MyApplication).productViewModel.insert(product)
        presenter.showResult()
    }

    override fun updateItem(product: ProductEntity, activity: Activity) {
        (activity.application as MyApplication).productViewModel.update(product)
        presenter.showResult()
    }

    override fun getItem(idItem: Long, activity: Activity) {

        val productViewModel = (activity.application as MyApplication).productViewModel

        productViewModel.getItem(idItem).observe(activity as LifecycleOwner, Observer { products ->
            // Update the cached copy of the items in the adapter.
            products.let {
                presenter.showItem(it)
            }
        })
    }

}