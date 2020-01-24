package com.ma.businessinventory.ui.search

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

interface Search {

    interface View {
        fun showItems(items: List<ProductEntity>)
    }

    interface Presenter {
        fun getItems(activity: Activity)
        fun showItems(items: List<ProductEntity>)
    }

    interface Model {
        fun getItems(activity: Activity)
    }

}
