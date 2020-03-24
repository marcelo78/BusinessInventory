package com.ma.businessinventory.ui.detailitem

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.ProductEntity
import com.ma.businessinventory.ui.adddetailitem.AddDetailItemActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 *
 */
class ItemDetailActivity : AppCompatActivity(), ICommunicator, IItemDetail.View {

    private var idItem = 0L
    private lateinit var presenter: IItemDetail.Presenter

    companion object {
        private val TAG = ItemDetailActivity::class.java.simpleName

        fun getStartIntent(context: Context): Intent {
            return Intent(context, ItemDetailActivity::class.java)
        }

        const val ItemId = "PRODUCT_ID"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(detail_toolbar)

        presenter = ItemDetailPresenter(this)

        fab.setOnClickListener {

            val intentDetail = AddDetailItemActivity.getStartIntent(this).apply {
                putExtra(ItemId, idItem)
            }
            startActivity(intentDetail)

        }

        btnDelete.setOnClickListener {
            Log.d(TAG, "btnDelete.setOnClickListener")
            showAlert()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ItemDetailFragment()
                .apply {
                    arguments = Bundle().apply {
                        putLong(
                            ItemId,
                            intent.getLongExtra(ItemId, 0L)
                        )
                    }
                }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun loadCoverImage(idItem: Long, path: String) {
        Log.d(TAG, path)
        this.idItem = idItem
        Picasso.get()
            .load(path)
            .placeholder(R.drawable.ic_photo_white_100dp)
            .error(R.drawable.ic_photo_white_100dp)
            .fit()
            .centerCrop()
            .into(ivCover)
    }

    override fun populate(product: List<ProductEntity>) {

    }

    override fun showResult() {
        Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this@ItemDetailActivity)
        builder.setTitle(getString(R.string.dialog_delete_item_title))
        builder.setMessage(getString(R.string.dialog_delete_item_message))
        builder.setPositiveButton(getString(R.string.dialog_delete_item_yes)) { _, _ ->
            val product = ProductEntity(idItem)
            presenter.deleteItem(product, this)
        }
        builder.setNegativeButton(getText(R.string.dialog_delete_item_no)) { _, _ ->
            Log.d(TAG, "You are not agree.")
        }
        builder.setNeutralButton(getString(R.string.dialog_delete_item_cancel)) { _, _ ->
            Log.d(TAG, "You cancelled the dialog.")
        }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

}
