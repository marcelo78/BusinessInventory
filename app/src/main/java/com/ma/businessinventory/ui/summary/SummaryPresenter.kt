package com.ma.businessinventory.ui.summary

import android.app.Activity
import com.ma.businessinventory.db.entities.SummaryEntity

class SummaryPresenter(private var view: ISummary.View) : ISummary.Presenter {

    private var model: ISummary.Model = SummaryModel(this)

    override fun getSummary(activity: Activity) {
        model.getSummary(activity)
    }

    override fun showItems(items: List<SummaryEntity>) {
        view.showItems(items)
    }

}
