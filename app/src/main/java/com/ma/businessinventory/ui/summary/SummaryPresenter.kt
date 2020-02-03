package com.ma.businessinventory.ui.summary

import android.app.Activity
import com.ma.businessinventory.db.entity.SummaryEntity

class SummaryPresenter(private var view: Summary.View) : Summary.Presenter {

    private var model: Summary.Model = SummaryModel(this)

    override fun getSummary(activity: Activity) {
        model.getSummary(activity)
    }

    override fun showItems(items: List<SummaryEntity>) {
        view.showItems(items)
    }

}
