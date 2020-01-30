package com.ma.businessinventory.ui.summary

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ma.businessinventory.R
import com.ma.businessinventory.ui.ItemListActivity

/**
 *
 */
class SummaryFragment : Fragment() {

    private lateinit var btnAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("", "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_summary, container, false)

        btnAdd = view.findViewById(R.id.btnAdd)

        btnAdd.setOnClickListener {

            val intent = Intent(activity, ItemListActivity::class.java)
            activity?.startActivity(intent)

        }

        return view
    }

}
