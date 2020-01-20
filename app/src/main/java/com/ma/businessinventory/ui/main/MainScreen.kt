package com.ma.businessinventory.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.ma.businessinventory.R

enum class MainScreen(
    @IdRes val menuItemId: Int,
    @DrawableRes val menuItemIconId: Int,
    @StringRes val titleStringId: Int,
    val fragment: Fragment
) {
    LOGS(R.id.bottom_navigation_item_logs, R.drawable.ic_action_search, R.string.btn_serch, SearchFragment()),
    PROGRESS(R.id.bottom_navigation_item_progress, R.drawable.ic_action_summary, R.string.btn_summary, SummaryFragment()),
    PROFILE(R.id.bottom_navigation_item_profile, R.drawable.ic_action_export, R.string.btn_export, ExportFragment())
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}