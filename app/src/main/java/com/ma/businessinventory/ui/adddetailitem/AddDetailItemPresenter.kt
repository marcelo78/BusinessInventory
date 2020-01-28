package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

class AddDetailItemPresenter(private var view: AddDetailItem.View) : AddDetailItem.Presenter {

    private var model: AddDetailItem.Model = AddDetailItemModel(this)

    override fun insertItem(product: ProductEntity, activity: Activity) {
        model.insertItem(product, activity)
    }

    override fun showResult() {
        view.showResult()
    }

    override fun showError() {
        view.showError()
    }

}
