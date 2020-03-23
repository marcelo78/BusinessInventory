package com.ma.businessinventory.ui.adddetailitem

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_add_detail_item.*


class AddDetailItemActivity : AppCompatActivity(), IAddDetailItem.View {

    private var idItem: Long = 0

    private lateinit var presenter: IAddDetailItem.Presenter
    private lateinit var product: ProductEntity
    private lateinit var productOld: ProductEntity

    private var myOnFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
        if (!hasFocus) { // code to execute when EditText loses focus
            val editText = (view as EditText)
            Log.d(TAG, "$hasFocus id: $editText")
            fillData()
            presenter.updateData(product, editText.text.toString(), editText.id)
        }
    }

    companion object {
        private val TAG = AddDetailItemActivity::class.java.simpleName

        fun getStartIntent(context: Context): Intent {
            return Intent(context, AddDetailItemActivity::class.java)
        }

        const val ItemId = "PRODUCT_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail_item)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        presenter = AddDetailItemPresenter(this)

        product = ProductEntity()
        checkMode()

        btnSave.setOnClickListener {

            Log.v(TAG, "btnSave")
            product.apply {
                nameInventory = etName.text.toString()
                place = etPlace.text.toString()
                description = etDescription.text.toString()
                type = etType.text.toString()
                dateProduct = etDate.text.toString()
                barcode = etBarcode.text.toString()
                boughtNo = etBoughtNo.getValueDouble()
                soldNo = etSoldNo.getValueDouble()
                unidBuyPriceUS = etUnidBuyPrice.getValueInt()
                unidSellPriceUS = etUnidSellPrice.getValueInt()
                totalCostUS = etTotalCost.getValueDouble()
                totalReceivedUS = etTotalReceived.getValueInt()
                totalProfitUS = etTotalProfit.getValueInt()
                photo = etPhoto.text.toString()
            }

            val valid = presenter.validate(product)
            if (valid) {
                if (idItem == 0L) {
                    presenter.insertItem(product, this)
                } else {
                    presenter.updateItem(product, this)
                }
            }

        }
    }

    private fun EditText.getValueInt(): Int {
        return if (text.toString().isNotEmtyField()) {
            text.toString().toInt()
        } else {
            0
        }
    }

    private fun EditText.getValueDouble(): Double {
        return if (text.toString().isNotEmtyField()) {
            text.toString().toDouble()
        } else {
            0.0
        }
    }

    override fun onStart() {
        super.onStart()
        if (idItem != 0L) {
            presenter.getItem(idItem, this)
            etName.onFocusChangeListener = myOnFocusChangeListener
            etPlace.onFocusChangeListener = myOnFocusChangeListener
            etDescription.onFocusChangeListener = myOnFocusChangeListener
            etType.onFocusChangeListener = myOnFocusChangeListener
            etDate.onFocusChangeListener = myOnFocusChangeListener
            etBarcode.onFocusChangeListener = myOnFocusChangeListener
            etBoughtNo.onFocusChangeListener = myOnFocusChangeListener
            etSoldNo.onFocusChangeListener = myOnFocusChangeListener
            etUnidBuyPrice.onFocusChangeListener = myOnFocusChangeListener
            etUnidSellPrice.onFocusChangeListener = myOnFocusChangeListener
            etTotalCost.onFocusChangeListener = myOnFocusChangeListener
            etTotalReceived.onFocusChangeListener = myOnFocusChangeListener
            etPhoto.onFocusChangeListener = myOnFocusChangeListener
        }
    }

    override fun onBackPressed() {
        if (presenter.validateChangedField(productOld, product)) {
            showAlert()
        } else {
            finish()
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this@AddDetailItemActivity)
        builder.setTitle(getString(R.string.dialog_detail_title))
        builder.setMessage(getString(R.string.dialog_detail_message))
        builder.setPositiveButton(getString(R.string.dialog_detail_yes)) { _, _ ->
            finish()
        }
        builder.setNegativeButton(getText(R.string.dialog_detail_no)) { _, _ ->
            Log.d(TAG, "You are not agree.")
        }
        builder.setNeutralButton(getString(R.string.dialog_detail_cancel)) { _, _ ->
            Log.d(TAG, "You cancelled the dialog.")
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun checkMode() {
        idItem = intent.getLongExtra(ItemId, -1)
        Log.d(TAG, "onCreate ID: $idItem")
    }

    private fun String.isNotEmtyField(): Boolean = this.isNotEmpty()

    override fun showResult() {
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError() {
        Toast.makeText(this, "Unsuccessful", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun populate(product: List<ProductEntity>) {
        if (product.isNotEmpty()) {
            this.product = product[0]
            if (!::productOld.isInitialized) {
                productOld = product[0].copy()
            }
            product[0].apply {
                Log.d(TAG, "Name: $nameInventory")
                etName.setText(nameInventory)
                etPlace.setText(place)
                etDescription.setText(description)
                etType.setText(type)
                etDate.setText(dateProduct)
                etBarcode.setText(barcode)
                etBoughtNo.setText("$boughtNo")
                etSoldNo.setText("$soldNo")
                etUnidBuyPrice.setText("$unidBuyPriceUS")
                etUnidSellPrice.setText("$unidSellPriceUS")
                etTotalCost.setText("$totalCostUS")
                etTotalReceived.setText("$totalReceivedUS")
                etTotalProfit.setText("$totalProfitUS")
                etPhoto.setText(photo)
            }
        }
    }

    fun fillData() {
        product.nameInventory = etName.text.toString()
        product.place = etPlace.text.toString()
        product.description = etDescription.text.toString()
        product.type = etType.text.toString()
        product.dateProduct = etDate.text.toString()
        product.barcode = etBarcode.text.toString()
        product.boughtNo = etBoughtNo.text.toString().toDouble()
        product.soldNo = etSoldNo.text.toString().toDouble()
        product.unidBuyPriceUS = etUnidBuyPrice.text.toString().toInt()
        product.unidSellPriceUS = etUnidSellPrice.text.toString().toInt()
        product.totalCostUS = etTotalCost.text.toString().toDouble()
        product.totalReceivedUS = etTotalReceived.text.toString().toInt()
        product.totalProfitUS = etTotalProfit.text.toString().toInt()
        product.photo = etPhoto.text.toString()
    }

    override fun clearPreErrors() {
        etName.clearError()
        etPlace.clearError()
        etDescription.clearError()
        etType.clearError()
        etDate.clearError()
        etBarcode.clearError()
        etBoughtNo.clearError()
        etSoldNo.clearError()
        etUnidBuyPrice.clearError()
        etUnidSellPrice.clearError()
        etTotalCost.clearError()
        etTotalReceived.clearError()
        etTotalProfit.clearError()
        etPhoto.clearError()
    }

    private fun EditText.clearError() {
        error = null
        clearFocus()
    }

    override fun showErrorMessage(idView: Int, message: String) {
        val editText = findViewById<EditText>(idView)
        editText.error = message
        editText.requestFocus()
    }

}
