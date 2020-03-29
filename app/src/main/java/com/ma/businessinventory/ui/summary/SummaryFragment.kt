package com.ma.businessinventory.ui.summary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ma.businessinventory.R
import com.ma.businessinventory.db.entities.SummaryEntity
import com.ma.businessinventory.tools.NumberUtil
import kotlinx.android.synthetic.main.fragment_summary.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 */
class SummaryFragment : Fragment(), ISummary.View {

    companion object {
        private val TAG = SummaryFragment::class.java.simpleName
    }

    private val summaryPresenter: SummaryPresenter by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        summaryPresenter.getItems().observe(viewLifecycleOwner, Observer { summary ->
            showItems(summary)
        })

    }

    override fun showItems(items: List<SummaryEntity>) {

        Log.d(TAG, "Show items")
        if (items.isNotEmpty()) {
            val df = NumberUtil.getInstance()
            val item = items[0]
            viewCurrentUs.text = df?.format(item.data1)
            viewCurrentNo.text = df?.format(item.data2)
            viewInventorySold.text = df?.format(item.data3)
            viewProfitMargin.text = df?.format(((item.data4 / item.data5) * 100))
            viewAccumProfitsUs.text = df?.format((item.data1 - item.data4))
            viewNetProfitUs.text = df?.format(item.data4)
            viewTotalBoughtUs.text = df?.format(item.data5)
            viewTotalSoldUs.text = df?.format(item.data6)
        }

    }

}
