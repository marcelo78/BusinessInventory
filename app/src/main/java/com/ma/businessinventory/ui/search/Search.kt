package com.ma.businessinventory.ui.search

import android.app.Activity
import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.entities.ProductEntity

interface Search {

    interface View {
        fun showItems(items: MutableList<ProductEntity>)

        fun showFilterbyName(name: String)
    }

    interface Presenter {
        fun getItems(): LiveData<MutableList<ProductEntity>>

//        fun showItems(items: MutableList<ProductEntity>)
    }

    interface Model {
        fun getItems(activity: Activity)
    }

}
