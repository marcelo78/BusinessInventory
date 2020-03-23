package com.ma.businessinventory.ui.main

interface IMain {

    interface View {

        fun openAddItemActivity(idItem: Int)

        fun openEditItemActivity(idItem: Int)

    }

    interface Presenter {}

    interface Model {}
}