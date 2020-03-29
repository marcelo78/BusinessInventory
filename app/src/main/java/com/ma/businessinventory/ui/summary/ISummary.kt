package com.ma.businessinventory.ui.summary

import android.app.Activity
import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.entities.SummaryEntity

interface ISummary {

    interface View {
        fun showItems(items: List<SummaryEntity>)
    }

    interface Presenter {
        fun getItems(): LiveData<MutableList<SummaryEntity>>
    }

}
