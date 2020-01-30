package com.ma.businessinventory.ui.main

interface Main {

    interface View {

        fun openAddItemActivity(idItem: Int)

        fun openEditItemActivity(idItem: Int)

    }

    interface Presenter {}

    interface Model {}
}