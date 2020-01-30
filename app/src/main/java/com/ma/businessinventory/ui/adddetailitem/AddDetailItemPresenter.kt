package com.ma.businessinventory.ui.adddetailitem

import android.app.Activity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity

class AddDetailItemPresenter(private var view: AddDetailItem.View) : AddDetailItem.Presenter {

    private var model: AddDetailItem.Model = AddDetailItemModel(this)

    override fun insertItem(product: ProductEntity, activity: Activity) {
        model.insertItem(product, activity)
    }

    override fun updateItem(product: ProductEntity, activity: Activity) {
        model.updateItem(product, activity)
    }

    override fun deleteItem(product: ProductEntity, activity: Activity) {
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

}
