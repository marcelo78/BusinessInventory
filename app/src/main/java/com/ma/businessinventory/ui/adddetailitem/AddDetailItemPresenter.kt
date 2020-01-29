package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

class AddDetailItemPresenter(private var view: AddDetailItem.View) : AddDetailItem.Presenter {

    private var model: AddDetailItem.Model = AddDetailItemModel(this)

    override fun insertItem(product: ProductEntity, activity: Activity) {
        model.insertItem(product, activity)
    }

    override fun updateItem(product: ProductEntity, activity: Activity) {
        model.updateItem(product, activity)
    }

    override fun showResult() {
        view.showResult()
    }

    override fun showError() {
        view.showError()
    }

    override fun getItem(idItem: Long, activity: Activity) {
        model.getItem(idItem, activity)
    }

    override fun showItem(product: List<ProductEntity>) {
        view.populate(product)
    }

    override fun validate(product: ProductEntity): Boolean {
        view.clearPreErrors()
        if (product.nameInventory!!.isEmpty()){
            view.showErrorMessage()
            return false
        }
        return true
    }

}
