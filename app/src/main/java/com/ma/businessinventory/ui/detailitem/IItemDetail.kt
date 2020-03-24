package com.ma.businessinventory.ui.detailitem

import android.app.Activity
import com.ma.businessinventory.db.entities.ProductEntity

interface IItemDetail {

    interface View {

        fun populate(product: List<ProductEntity>)

        fun showResult()

    }

    interface Presenter {

        fun getItem(idItem: Long, activity: Activity)

        fun showItem(product: List<ProductEntity>)

        fun deleteItem(product: ProductEntity, activity: Activity)

        fun showResult()
    }

    interface Model {

        fun getItem(idItem: Long, activity: Activity)

        fun deleteItem(product: ProductEntity, activity: Activity)

    }

}
