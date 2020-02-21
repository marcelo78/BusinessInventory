package com.ma.businessinventory.ui.export

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

interface Export {

    interface View {
        fun showResult(items: MutableList<ProductEntity>)
    }

    interface Presenter {
        fun getItems(activity: Activity)

        fun showItems(items: MutableList<ProductEntity>)
    }

    interface Model {
        fun getItems(activity: Activity)
    }

}
