package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.MyApplication
import com.ma.businessinventory.db.entity.ProductEntity

class AddDetailItemModel(private var presenter: AddDetailItem.Presenter) : AddDetailItem.Model {

    override fun insertItem(product: ProductEntity, activity: Activity) {
        (activity!!.application as MyApplication).productViewModel.insert(product)
        presenter.showResult()
    }

}