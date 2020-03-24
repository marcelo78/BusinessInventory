package com.ma.businessinventory

import android.app.Application
import com.ma.businessinventory.di.appModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    //    lateinit var productViewModel: ProductViewModel
    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}
