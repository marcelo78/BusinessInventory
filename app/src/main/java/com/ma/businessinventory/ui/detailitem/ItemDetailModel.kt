package com.ma.businessinventory.ui.detailitem

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.ma.businessinventory.MyApplication
import com.ma.businessinventory.db.entity.ProductEntity

class ItemDetailModel(private var presenter: ItemDetail.Presenter) : ItemDetail.Model {

    override fun getItem(idItem: Long, activity: Activity) {
        val productViewModel = (activity.application as MyApplication).productViewModel

        productViewModel.getItem(idItem).observe(activity as LifecycleOwner, Observer { products ->
            // Update the cached copy of the items in the adapter.
            products.let {
                presenter.showItem(it)
            }
        })
    }

    override fun deleteItem(product: ProductEntity, activity: Activity) {
        (activity.application as MyApplication).productViewModel.delete(product)
        presenter.showResult()
    }

}
