package com.ma.businessinventory.ui.detailitem

import android.app.Activity
import com.ma.businessinventory.db.entities.ProductEntity

class ItemDetailModel(private var presenter: IItemDetail.Presenter) : IItemDetail.Model {

    override fun getItem(idItem: Long, activity: Activity) {
//        val productViewModel = (activity.application as MyApplication).productViewModel

//        productViewModel.getItem(idItem).observe(activity as LifecycleOwner, Observer { products ->
//            // Update the cached copy of the items in the adapter.
//            products.let {
//                presenter.showItem(it)
//            }
//        })
    }

    override fun deleteItem(product: ProductEntity, activity: Activity) {
//        (activity.application as MyApplication).productViewModel.delete(product)
        presenter.showResult()
    }

}
