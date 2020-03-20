package com.ma.businessinventory.ui.summary

import android.app.Activity
import com.ma.businessinventory.db.entity.SummaryEntity

interface ISummary {

    interface View {
        fun showItems(items: List<SummaryEntity>)
    }

    interface Presenter {
        fun getSummary(activity: Activity)

        fun showItems(items: List<SummaryEntity>)
    }

    interface Model {
        fun getSummary(activity: Activity)
    }
}
