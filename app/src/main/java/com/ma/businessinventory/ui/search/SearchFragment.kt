package com.ma.businessinventory.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ma.businessinventory.R
import com.ma.businessinventory.adapter.ItemAdapter
import com.ma.businessinventory.db.entity.ProductEntity

/**
 *
 */
class SearchFragment : Fragment(), Search.View {

    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var listItems: RecyclerView

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
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        listItems = view.findViewById(R.id.listItems)
        listItems.layoutManager = LinearLayoutManager(context)
        presenter.getItems()
        return view
    }

    override fun showItems(items: List<ProductEntity>) {
        val context = activity as Context

        val itemAdapter = ItemAdapter(items, context)
        listItems.adapter = itemAdapter
    }

}
