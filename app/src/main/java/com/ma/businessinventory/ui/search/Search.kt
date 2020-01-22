package com.ma.businessinventory.ui.search

import com.ma.businessinventory.db.entity.ProductEntity

interface Search {

    interface View {
        fun showItems(items: List<ProductEntity>)
    }

    interface Presenter {
        fun getItems()
        fun showItems(items: List<ProductEntity>)
    }

    interface Model {
        fun getItems()
    }

}
