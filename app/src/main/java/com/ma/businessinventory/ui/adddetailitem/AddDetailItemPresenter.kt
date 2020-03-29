package com.ma.businessinventory.ui.adddetailitem

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.db.interactor.IProductInteractor
import io.reactivex.Observable
import java.util.*

class AddDetailItemPresenter(private val iProductInteractor: IProductInteractor) :
    IAddDetailItem.Presenter, ViewModel() {

    override fun insertItem(product: ProductEntity) {
        Log.d("AddDetailItemPresenter", "insertItem")
        iProductInteractor.insert(product)
    }

    override fun updateItem(product: ProductEntity) {
        iProductInteractor.update(product)
    }

    override fun getItem(idItem: Long): Observable<ProductEntity> {
        return iProductInteractor.loadAllByIds(idItem)
    }

    override fun validate(product: ProductEntity): Map<String, Int> {

        if (product.nameInventory!!.isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etName, "message" to R.string.error_message)
        }
        if (product.place!!.isEmpty()) {
            return mutableMapOf("isValid" to 0, "view" to R.id.etPlace, "message" to R.string.error_message)
        }
        if (product.description!!.isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etDescription, "message" to R.string.error_message)
        }
        if (product.type!!.isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etType, "message" to R.string.error_message)
        }
        if (product.dateProduct!!.isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etDate, "message" to R.string.error_message)
        }
        if (product.barcode!!.isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etBarcode, "message" to R.string.error_message)
        }
        if (product.boughtNo!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etBoughtNo, "message" to R.string.error_message)
        }
        if (product.soldNo!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etSoldNo, "message" to R.string.error_message)
        }
        if (product.unidBuyPriceUS!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etUnidBuyPrice, "message" to R.string.error_message)
        }
        if (product.unidSellPriceUS!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etUnidSellPrice, "message" to R.string.error_message)
        }
        if (product.totalCostUS!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etTotalCost, "message" to R.string.error_message)
        }
        if (product.totalReceivedUS!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etTotalReceived, "message" to R.string.error_message)
        }
        if (product.totalProfitUS!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etTotalProfit, "message" to R.string.error_message)
        }
        if (product.photo!!.toString().isEmpty()) {
            return mapOf("isValid" to 0, "view" to R.id.etPhoto, "message" to R.string.error_message)
        }
        return mapOf("isValid" to 1, "view" to 0, "message" to 0)
    }

    override fun validateChangedField(productOld: ProductEntity, product: ProductEntity): Boolean {
        return product != productOld
    }

    override fun updateData(product: ProductEntity, value: String, idEditText: Int): ProductEntity {
        when (idEditText) {
            R.id.etBoughtNo, R.id.etSoldNo -> {
                if (idEditText.compareTo(R.id.etBoughtNo) == 0) {
                    product.boughtNo = value.toDouble()
                } else {
                    product.soldNo = value.toDouble()
                }
                product.totalCostUS = product.boughtNo?.times(product.unidBuyPriceUS!!)

                product.totalReceivedUS = product.soldNo?.times(product.unidSellPriceUS!!)?.toInt()

                product.totalProfitUS =
                    product.totalReceivedUS?.minus(product.totalCostUS!!.toInt())
            }
            R.id.etUnidBuyPrice -> {
                product.unidBuyPriceUS = value.toInt()
                product.totalCostUS = product.boughtNo?.times(product.unidBuyPriceUS!!)
                product.totalProfitUS =
                    product.totalReceivedUS?.minus(product.totalCostUS!!.toInt())
            }
            R.id.etUnidSellPrice -> {
                product.unidSellPriceUS = value.toInt()
                product.totalReceivedUS = product.soldNo?.toInt()?.times(product.unidSellPriceUS!!)
                product.totalProfitUS =
                    product.totalReceivedUS?.minus(product.totalCostUS!!.toInt())

            }
            R.id.etTotalCost -> {
                product.totalCostUS = value.toDouble()
                val soldNo = product.soldNo ?: 0.0
                val unidSellPriceUs = product.unidSellPriceUS ?: 0
                if (soldNo > 0 && unidSellPriceUs > 0) {
                    product.totalReceivedUS =
                        product.soldNo?.toInt()?.times(product.unidSellPriceUS!!)
                }
                val totalCostUS = product.totalCostUS ?: 0.0
                if (totalCostUS > 0) {
                    product.totalProfitUS =
                        product.totalReceivedUS?.minus(product.totalCostUS!!.toInt())

                }
                product.unidBuyPriceUS = 0
            }
            R.id.etTotalReceived -> {
                product.totalReceivedUS = value.toInt()
                val totalCostUS = product.totalCostUS ?: 0.0
                if (totalCostUS > 0) {
                    product.totalProfitUS =
                        product.totalReceivedUS?.minus(product.totalCostUS!!.toInt())

                }
                product.unidSellPriceUS = 0
            }
        }

//        view.populate(listOf(product))
        return product
    }

}
