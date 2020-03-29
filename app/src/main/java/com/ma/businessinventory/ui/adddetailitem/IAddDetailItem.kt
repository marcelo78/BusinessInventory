package com.ma.businessinventory.ui.adddetailitem

import com.ma.businessinventory.db.entities.ProductEntity
import io.reactivex.Observable

interface IAddDetailItem {

    interface View {
        fun showResult()

        fun showError()

        fun populate(product: ProductEntity)

        fun clearPreErrors()

        fun showErrorMessage(idView: Int, message: String)
    }

    interface Presenter {
        fun insertItem(product: ProductEntity)

        fun updateItem(product: ProductEntity)

        fun getItem(idItem: Long): Observable<ProductEntity>

        fun validate(product: ProductEntity): Map<String, Int>

        fun validateChangedField(productOld: ProductEntity, product: ProductEntity): Boolean

        fun updateData(product: ProductEntity, value: String, idEditText: Int): ProductEntity
    }

}
