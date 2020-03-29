package com.ma.businessinventory

import android.app.Application
import com.ma.businessinventory.di.appModule
import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}
