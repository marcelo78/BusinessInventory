package com.ma.businessinventory.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ma.businessinventory.R
import com.ma.businessinventory.adapter.ItemAdapter
import com.ma.businessinventory.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 */
class SearchFragment : Fragment() {

    companion object {
        private val TAG = SearchFragment::class.java.simpleName
    }

    private val searchPresenter: SearchPresenter by viewModel()

    private lateinit var itemAdapter: ItemAdapter

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

        fabAddItem.setOnClickListener {
            (activity as MainActivity).openEditItemActivity(0)
        }

        searchPresenter.getItems().observe(viewLifecycleOwner, Observer { products ->
            Log.d("initializeUI", "size ${products.size}")
            itemAdapter = activity?.let { ItemAdapter(products, it) }!!
            listItems.adapter = itemAdapter
        })
    }

}
