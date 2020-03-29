package com.ma.businessinventory.ui.export

import android.app.Activity
import com.ma.businessinventory.db.entities.ProductEntity

interface IExport {

    interface View

    interface Presenter {
        fun getItems(activity: Activity)

        fun showItems(items: MutableList<ProductEntity>)
    }

}
