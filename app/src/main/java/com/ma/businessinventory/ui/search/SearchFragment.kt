package com.ma.businessinventory.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ma.businessinventory.R
import com.ma.businessinventory.adapter.ItemAdapter
import com.ma.businessinventory.db.entity.ProductEntity
import com.ma.businessinventory.ui.main.MainActivity

/**
 *
 */
class SearchFragment : Fragment(), Search.View {

    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private lateinit var listItems: RecyclerView

    private lateinit var presenter: Search.Presenter

    private lateinit var fabAddItem: FloatingActionButton

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
        fabAddItem = view.findViewById(R.id.fabAddItem)
        init()
        return view
    }

    fun init() {
        listItems.layoutManager = LinearLayoutManager(context)
        presenter.getItems(activity!!)
        fabAddItem.setOnClickListener {
            (activity as MainActivity).openAddItemActivity(0)
        }
    }

    override fun showItems(items: List<ProductEntity>) {
        val context = activity as Context

        val itemAdapter = ItemAdapter(items, context)
        listItems.adapter = itemAdapter
    }

}
