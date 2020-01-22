package com.ma.businessinventory.ui.search

import com.ma.businessinventory.db.entity.ProductEntity

class SearchModel(private var presenter: Search.Presenter) : Search.Model {

    override fun getItems() {
        val items: List<ProductEntity>
        val product1 =
            ProductEntity(
                0, "Hello1", "Hello1", "Test1", "Test2", "2020-01-22",
                "", 10.0, 10.0, 5, 10,
                15.0, 10, 10, ""
            )
        val product2 =
            ProductEntity(
                1, "Hello2", "Hello2", "Test2", "Test2", "2020-01-20",
                "", 15.0, 11.0, 8, 4,
                20.0, 11, 12, ""
            )
        items = listOf(product1, product2)
        presenter.showItems(items)
    }

}