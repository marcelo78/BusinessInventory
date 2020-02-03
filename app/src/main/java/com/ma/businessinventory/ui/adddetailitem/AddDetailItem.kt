package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.db.entity.ProductEntity

interface AddDetailItem {

    interface View {
        fun showResult()

        fun showError()

        fun populate(product: List<ProductEntity>)

        fun clearPreErrors()

        fun showErrorMessage(idView: Int, message: String)
    }

    interface Presenter {
        fun insertItem(product: ProductEntity, activity: Activity)

        fun updateItem(product: ProductEntity, activity: Activity)

        fun deleteItem(product: ProductEntity, activity: Activity)

        fun showResult()

        fun showError()

        fun getItem(idItem: Long, activity: Activity)

        fun showItem(product: List<ProductEntity>)

        fun validate(product: ProductEntity): Boolean

        fun validateChangedField(productOld: ProductEntity, product: ProductEntity): Boolean

        fun updateData(product: ProductEntity, value: String, idEditText: Int)
    }

    interface Model {
        fun insertItem(product: ProductEntity, activity: Activity)

        fun updateItem(product: ProductEntity, activity: Activity)

        fun deleteItem(product: ProductEntity, activity: Activity)

        fun getItem(idItem: Long, activity: Activity)
    }

}