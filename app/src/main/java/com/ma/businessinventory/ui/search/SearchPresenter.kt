package com.ma.businessinventory.ui.search

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

class SearchPresenter(private var view: Search.View) : Search.Presenter {

    private var model: Search.Model = SearchModel(this)

    override fun getItems(activity: Activity) {
        model.getItems(activity)
    }

    override fun showItems(items: List<ProductEntity>) {
        view.showItems(items)
    }

}
