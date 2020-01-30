package com.ma.businessinventory.ui.detailitem

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity
import com.ma.businessinventory.ui.adddetailitem.AddDetailItemActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 *
 */
class ItemDetailActivity : AppCompatActivity(), Communicator, ItemDetail.View {

    private var idItem = 0L
    private lateinit var presenter: ItemDetail.Presenter

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
            val product = ProductEntity(idItem)
            presenter.deleteItem(product, this)
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

}
