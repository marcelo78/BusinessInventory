package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.ProductEntity

class AddDetailItemPresenter(private var view: IAddDetailItem.View) : IAddDetailItem.Presenter {

    private var model: IAddDetailItem.Model = AddDetailItemModel(this)

    override suspend fun insertItem(product: ProductEntity, activity: Activity) {
        model.insertItem(product, activity)
    }

    override fun updateItem(product: ProductEntity, activity: Activity) {
        model.updateItem(product, activity)
    }

    override suspend fun deleteItem(product: ProductEntity, activity: Activity) {
        model.deleteItem(product, activity)
    }

    override fun showResult() {
        view.showResult()
    }

    override fun showError() {
        view.showError()
    }

    override fun getItem(idItem: Long, activity: Activity) {
        model.getItem(idItem, activity)
    }

    override fun showItem(product: List<ProductEntity>) {
        view.populate(product)
    }

    override fun validate(product: ProductEntity): Boolean {
        view.clearPreErrors()
        val activity = (view as Activity)
        if (product.nameInventory!!.isEmpty()) {
            view.showErrorMessage(R.id.etName, activity.getString(R.string.error_message))
            return false
        }
        if (product.place!!.isEmpty()) {
            view.showErrorMessage(R.id.etPlace, activity.getString(R.string.error_message))
            return false
        }
        if (product.description!!.isEmpty()) {
            view.showErrorMessage(R.id.etDescription, activity.getString(R.string.error_message))
            return false
        }
        if (product.type!!.isEmpty()) {
            view.showErrorMessage(R.id.etType, activity.getString(R.string.error_message))
            return false
        }
        if (product.dateProduct!!.isEmpty()) {
            view.showErrorMessage(R.id.etDate, activity.getString(R.string.error_message))
            return false
        }
        if (product.barcode!!.isEmpty()) {
            view.showErrorMessage(R.id.etBarcode, activity.getString(R.string.error_message))
            return false
        }
        if (product.boughtNo!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etBoughtNo, activity.getString(R.string.error_message))
            return false
        }
        if (product.soldNo!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etSoldNo, activity.getString(R.string.error_message))
            return false
        }
        if (product.unidBuyPriceUS!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etUnidBuyPrice, activity.getString(R.string.error_message))
            return false
        }
        if (product.unidSellPriceUS!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etUnidSellPrice, activity.getString(R.string.error_message))
            return false
        }
        if (product.totalCostUS!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etTotalCost, activity.getString(R.string.error_message))
            return false
        }
        if (product.totalReceivedUS!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etTotalReceived, activity.getString(R.string.error_message))
            return false
        }
        if (product.totalProfitUS!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etTotalProfit, activity.getString(R.string.error_message))
            return false
        }
        if (product.photo!!.toString().isEmpty()) {
            view.showErrorMessage(R.id.etPhoto, activity.getString(R.string.error_message))
            return false
        }
        return true
    }

    override fun validateChangedField(productOld: ProductEntity, product: ProductEntity): Boolean {
        return product != productOld
    }

    override fun updateData(product: ProductEntity, value: String, idEditText: Int) {
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

        view.populate(listOf(product))
    }

}
