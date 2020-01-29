package com.ma.businessinventory.ui.adddetailitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.android.synthetic.main.activity_add_detail_item.*


class AddDetailItemActivity : AppCompatActivity(), AddDetailItem.View {

    private var idItem: Long = -1

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

        product = ProductEntity(null)
        checkMode()

        btnSave.setOnClickListener {

            Log.v(TAG, "btnSave")
            product.apply {
                nameInventory = etName.text.toString()
//                place = etPlace.text.toString()
//                description = etDescription.text.toString()
//                type = etType.text.toString()
//                dateProduct = etDate.text.toString()
//                barcode = etBarcode.text.toString()
//                boughtNo = etBoughtNo.text.toString().toDouble()
//                soldNo = etSoldNo.text.toString().toDouble()
//                unidBuyPriceUS = etUnidBuyPrice.text.toString().toInt()
//                unidSellPriceUS = etUnidSellPrice.text.toString().toInt()
//                totalCostUS = etTotalCost.text.toString().toDouble()
//                totalReceivedUS = etTotalReceived.text.toString().toInt()
//                totalProfitUS = etTotalProfit.text.toString().toInt()
//                photo = etPhoto.text.toString()
            }

            val valid = presenter.validate(product)
            if (valid) {
                if (idItem == -1L) {
                    presenter.insertItem(product, this)
                } else {
                    presenter.updateItem(product, this)
                }
            }

        }

//        init()
    }

    override fun onStart() {
        super.onStart()
        if(idItem != -1L){
            presenter.getItem(idItem, this)
        }
    }

//    private fun init() {
//        etName.validate("Name required") { s -> s.isNotEmtyField() }
////        etName.validate("Name required") { s -> s.isNotEmtyField() }
//    }

    private fun checkMode() {
        idItem = intent.getIntExtra(ItemId, -1).toLong()
        Log.v(TAG, "onCreate $idItem")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.v(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.add_detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.v(TAG, "onPrepareOptionsMenu")
        menu?.getItem(0)?.isVisible = idItem != -1L
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

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                afterTextChanged.invoke(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun EditText.validate(message: String, validator: (String) -> Boolean) {
        this.afterTextChanged {
            this.error = if (validator(it)) null else message
        }
        this.error = if (validator(this.text.toString())) {
            null
        } else {
            message
        }
    }

//    private fun String.isValidEmail(): Boolean =
//        this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isNotEmtyField(): Boolean =
        this.isNotEmpty()

    override fun showResult() {
        Toast.makeText(this, "Inserded", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun populate(product: List<ProductEntity>) {
        if (product.isNotEmpty()) {
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
        etName.apply {
            error = null
            clearFocus()
        }
//        etPlace.error = ""
//        etDescription.error = ""
//        etType.error = ""
//        etDate.error = ""
//        etBarcode.error = ""
//        etBoughtNo.error = ""
//        etSoldNo.error = ""
//        etUnidBuyPrice.error = ""
//        etUnidSellPrice.error = ""
//        etTotalCost.error = ""
//        etTotalReceived.error = ""
//        etTotalProfit.error = ""
//        etPhoto.error = ""
    }

    override fun showErrorMessage() {
        etName.error = "Invalid Name"
    }

}
