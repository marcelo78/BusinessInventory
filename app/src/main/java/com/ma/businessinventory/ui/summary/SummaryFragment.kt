package com.ma.businessinventory.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ma.businessinventory.MyApplication
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.ProductEntity

/**
 *
 */
class SummaryFragment : Fragment() {

    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        btnAdd = view.findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {

            val product =
                ProductEntity(
                    null, "Hello1", "Place1", "Description1", "Type1", "2020-01-22",
                    "", 10.0, 10.0, 5, 10,
                    15.0, 10, 10, ""
                )
            (activity!!.application as MyApplication).productViewModel.insert(product)

        }

        return view
    }

}
