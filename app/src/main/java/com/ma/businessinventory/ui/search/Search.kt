package com.ma.businessinventory.ui.search

import android.app.Activity
import androidx.lifecycle.LiveData
import com.ma.businessinventory.db.entities.ProductEntity

interface Search {

    interface Presenter {
        fun getItems(): LiveData<MutableList<ProductEntity>>
    }

}
