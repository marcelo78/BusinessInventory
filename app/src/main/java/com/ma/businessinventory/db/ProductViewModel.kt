package com.ma.businessinventory.db

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ma.businessinventory.db.entity.ProductEntity
import kotlinx.coroutines.launch

class ProductViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: ProductRepository

    // LiveData gives us updated words when they change.
    val allProducts: LiveData<List<ProductEntity>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        // the correct WordRepository.
        val productDao = AppDatabase.getDatabase(application, viewModelScope).productDao()
        repository = ProductRepository(productDao)
        allProducts = repository.allProducts
    }

    /**
     * The implementation of insert() in the database is completely hidden from the UI.
     * Room ensures that you're not doing any long running operations on the mainthread, blocking
     * the UI, so we don't need to handle changing Dispatchers.
     * ViewModels have a coroutine scope based on their lifecycle called viewModelScope which we
     * can use here.
     */
    fun insert(product: ProductEntity) = viewModelScope.launch {
        repository.insert(product)
    }

    fun update(product: ProductEntity) = viewModelScope.launch {
        var returnInt = repository.update(product)
        Log.d("ProductViewModel", "---------------------:$returnInt:----------------------------")
    }

    fun getItem(ids: Long): LiveData<List<ProductEntity>> {
        return repository.getItem(ids)
    }
}