package com.ma.businessinventory.ui.export

import android.app.Activity
import android.util.Log
import com.ma.businessinventory.db.entity.ProductEntity

class ExportPresenter(private var view: IExport.View) : IExport.Presenter {

    private var model: IExport.Model = ExportModel(this)

    override fun getItems(activity: Activity) {
        model.getItems(activity)
    }

    override fun showItems(items: MutableList<ProductEntity>) {
        Log.d("ExportPresenter", "Items: ${items.size}")

    }

}
