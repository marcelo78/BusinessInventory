package com.ma.businessinventory.ui.detailitem

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

class ItemDetailPresenter(private var view: ItemDetail.View) : ItemDetail.Presenter {

    private var model: ItemDetail.Model = ItemDetailModel(this)

    override fun getItem(idItem: Long, activity: Activity) {
        model.getItem(idItem, activity)
    }

    override fun showItem(product: List<ProductEntity>) {
        view.populate(product)
    }

    override fun deleteItem(product: ProductEntity, activity: Activity) {
        model.deleteItem(product, activity)
    }

    override fun showResult() {
        view.showResult()
    }

}