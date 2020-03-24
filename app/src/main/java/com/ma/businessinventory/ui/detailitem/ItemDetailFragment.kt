package com.ma.businessinventory.ui.detailitem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.ProductEntity
import kotlinx.android.synthetic.main.activity_item_detail.*

/**
 *
 */
class ItemDetailFragment : Fragment(), IItemDetail.View {

    private lateinit var presenter: IItemDetail.Presenter
    private lateinit var comm: ICommunicator

    private lateinit var tvDate: TextView
    private lateinit var tvPrice: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvPlace: TextView
    private lateinit var tvType: TextView
    private var idItem = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        comm = activity as ICommunicator
        presenter = ItemDetailPresenter(this)

        arguments?.let {
            if (it.containsKey(ITEM_ID)) {
                Log.d("ItemDetailFragment", "id:${it.getLong(ITEM_ID, 0L)}")
                idItem = it.getLong(ITEM_ID, 0L)
                if (idItem != 0L) {
                    presenter.getItem(idItem, activity!!)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        tvDate = rootView.findViewById(R.id.tvDate)
        tvPrice = rootView.findViewById(R.id.tvPrice)
        tvDescription = rootView.findViewById(R.id.tvDescription)
        tvPlace = rootView.findViewById(R.id.tvPlace)
        tvType = rootView.findViewById(R.id.tvType)

        return rootView
    }

    companion object {

        const val ITEM_ID = "PRODUCT_ID"

    }

    override fun populate(product: List<ProductEntity>) {

        if (product.isNotEmpty()) {
            product.first().let {
                activity?.toolbar_layout?.title = it.nameInventory
                it.photo?.let { it1 -> comm.loadCoverImage(idItem, it1) }
                tvDate.text = it.dateProduct
                tvPrice.text = activity?.getString(
                    R.string.list_item_text_cost_item,
                    it.unidSellPriceUS.toString()
                ) ?: it.unidSellPriceUS.toString()
                tvDescription.text = it.description
                tvPlace.text = it.place
                tvType.text = it.type
            }
        }

    }

    override fun showResult() {
        Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
        activity!!.finish()
    }
}
