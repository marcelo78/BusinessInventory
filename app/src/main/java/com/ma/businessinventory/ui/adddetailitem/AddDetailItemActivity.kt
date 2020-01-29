package com.ma.businessinventory.ui.adddetailitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_add_detail_item.*


class AddDetailItemActivity : AppCompatActivity(), AddDetailItem.View {

    private var idItem: Long = 0

    private lateinit var presenter: AddDetailItem.Presenter
    private lateinit var product: ProductEntity

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
        }
    }

    private fun checkMode() {
        idItem = intent.getLongExtra(ItemId, -1)
        Log.v(TAG, "onCreate ID: $idItem")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.v(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.add_detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.v(TAG, "onPrepareOptionsMenu")
        menu?.getItem(0)?.isVisible = idItem != 0L
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_delete -> {
            // User chose the "Settings" item, show the app settings UI...
            Log.v(TAG, " ${item.title}")
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Log.v(TAG, "any")
            super.onOptionsItemSelected(item)
        }
    }

    private fun String.isNotEmtyField(): Boolean = this.isNotEmpty()

    override fun showResult() {
        Toast.makeText(this, "Inserded", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populate(product: List<ProductEntity>) {
        if (product.isNotEmpty()) {
            this.product = product[0]
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
