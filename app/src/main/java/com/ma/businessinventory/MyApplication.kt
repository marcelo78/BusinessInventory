package com.ma.businessinventory

import android.app.Application
import com.ma.businessinventory.db.ProductViewModel

class MyApplication : Application() {

    lateinit var productViewModel: ProductViewModel

}