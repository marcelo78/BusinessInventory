package com.ma.businessinventory.ui.search

import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.entities.ProductEntity

interface Search {

    interface View {
        fun showFilterbyName(name: String)
    }

    interface Presenter {
        fun getItems(): LiveData<MutableList<ProductEntity>>
    }

}
