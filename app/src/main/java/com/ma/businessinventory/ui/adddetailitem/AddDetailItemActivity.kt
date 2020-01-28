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
import java.util.*


class AddDetailItemActivity : AppCompatActivity(), AddDetailItem.View {

    private var idItem: Int = -1

    private lateinit var presenter: AddDetailItem.Presenter
    private lateinit var product: ProductEntity

    companion object {
        private val TAG = AddDetailItemActivity::class.java.simpleName

        fun getStartIntent(context: Context): Intent {
            return Intent(context, AddDetailItemActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_detail_item)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        presenter = AddDetailItemPresenter(this)

//        btnSave.isEnabled = false

        val date = Date()

        val current = date.time
        product = ProductEntity(
            null, "", "", "", "", current.toString(),
            "", 10.0, 10.0, 5, 10,
            15.0, 10, 10, ""
        )

        idItem = intent.getIntExtra("idItem", -1)
        Log.v(TAG, "onCreate $idItem")

        btnSave.setOnClickListener {

            Log.v(TAG, "btnSave")
            product.nameInventory = etName.text.toString()
            presenter.insertItem(product, this)

        }

        init()
    }

    private fun init() {
        etName.validate("Name required") { s -> s.isNotEmtyField() }
//        etName.validate("Name required") { s -> s.isNotEmtyField() }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.v(TAG, "onCreateOptionsMenu")
        menuInflater.inflate(R.menu.add_detail_menu, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.v(TAG, "onPrepareOptionsMenu")
        menu?.getItem(0)?.isVisible = idItem != -1
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

    private fun String.isValidEmail(): Boolean =
        this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

    private fun String.isNotEmtyField(): Boolean =
        this.isNotEmpty()

    override fun showResult() {
        Toast.makeText(this, "Inserded", Toast.LENGTH_SHORT).show()
        finish()
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
