package com.ma.businessinventory.ui.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entity.SummaryEntity
import kotlinx.android.synthetic.main.fragment_summary.*

/**
 *
 */
class SummaryFragment : Fragment(), Summary.View {

    companion object {
        private val TAG = SummaryFragment::class.java.simpleName
    }

    private lateinit var presenter: Summary.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SummaryPresenter(this)

        presenter.getSummary(activity!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun showItems(items: List<SummaryEntity>) {

        Log.d(TAG, "Show items")
        if (items.isNotEmpty()) {
            val item = items[0]
            viewCurrentUs.text = item.data1.toString()
            viewCurrentNo.text = item.data2.toString()
            viewInventorySold.text = item.data3.toString()
            viewProfitMargin.text = ((item.data4 / item.data5) * 100).toString()
            viewAccumProfitsUs.text = (item.data1 - item.data4).toString()
            viewNetProfitUs.text = item.data4.toString()
            viewTotalBoughtUs.text = item.data5.toString()
            viewTotalSoldUs.text = item.data6.toString()
        }

    }

}
