package com.ma.businessinventory.di

import androidx.room.Room
import com.ma.businessinventory.db.AppDatabase
import com.ma.businessinventory.db.interactor.IProductInteractor
import com.ma.businessinventory.db.interactor.ProductInteractor
import com.ma.businessinventory.db.repository.IProductRepository
import com.ma.businessinventory.db.repository.ProductRepository
import com.ma.businessinventory.ui.adddetailitem.AddDetailItemPresenter
import com.ma.businessinventory.ui.detailitem.ItemDetailPresenter
import com.ma.businessinventory.ui.search.SearchPresenter
import com.ma.businessinventory.ui.summary.SummaryPresenter
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, AppDatabase.DATABASE_NAME).build()
    }

    single { get<AppDatabase>().productDao() }

    single<IProductRepository> { ProductRepository(get()) }

    single<IProductInteractor> { ProductInteractor(get()) }

    viewModel { SearchPresenter(get()) }
    viewModel { ItemDetailPresenter(get()) }
    viewModel { AddDetailItemPresenter(get()) }
    viewModel { SummaryPresenter(get()) }

}
