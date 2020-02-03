package com.ma.businessinventory.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ma.businessinventory.R
import com.ma.businessinventory.adapter.ItemAdapter
import com.ma.businessinventory.db.entity.ProductEntity
import com.ma.businessinventory.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_search.*

/**
 *
 */
class SearchFragment : Fragment(), Search.View {

    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var presenter: Search.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SearchPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        listItems.layoutManager = LinearLayoutManager(context)
        presenter.getItems(activity!!)
        fabAddItem.setOnClickListener {
            (activity as MainActivity).openEditItemActivity(0)
        }
    }

    override fun showItems(items: List<ProductEntity>) {
        val context = activity as Context

        val itemAdapter = ItemAdapter(items, context)
        listItems.adapter = itemAdapter
    }

}
