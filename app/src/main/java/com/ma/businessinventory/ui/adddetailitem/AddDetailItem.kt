package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

interface AddDetailItem {

    interface View {
        fun showResult()
        fun showError()
    }

    interface Presenter {
        fun insertItem(product: ProductEntity, activity: Activity)

        fun showResult()

        fun showError()
    }

    interface Model {
        fun insertItem(product: ProductEntity, activity: Activity)
    }

}