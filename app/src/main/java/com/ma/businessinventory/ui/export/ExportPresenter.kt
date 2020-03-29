package com.ma.businessinventory.ui.export

import android.app.Activity
import android.util.Log
import com.ma.businessinventory.db.entities.ProductEntity

class ExportPresenter(private var view: IExport.View) : IExport.Presenter {

    override fun getItems(activity: Activity) {

    }

    override fun showItems(items: MutableList<ProductEntity>) {
        Log.d("ExportPresenter", "Items: ${items.size}")
    }

}
