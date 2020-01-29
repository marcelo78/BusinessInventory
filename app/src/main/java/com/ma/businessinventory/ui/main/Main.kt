package com.ma.businessinventory.ui.main

interface Main {

    interface View {
        fun openAddItemActivity(idItem: Int)
    }

    interface Presenter {}

    interface Model {}
}