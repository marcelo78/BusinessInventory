package com.ma.businessinventory.ui.search

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

interface Search {

    interface View {
        fun showItems(items: MutableList<ProductEntity>)

        fun showFilterbyName(name: String)
    }

    interface Presenter {
        fun getItems(activity: Activity)

        fun showItems(items: MutableList<ProductEntity>)
    }

    interface Model {
        fun getItems(activity: Activity)
    }

}
