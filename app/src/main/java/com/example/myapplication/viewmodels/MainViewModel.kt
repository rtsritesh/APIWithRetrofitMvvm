package com.example.myapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.adapter.ProductsAdapter
import com.example.myapplication.model.Product
import com.example.myapplication.repository.PopulationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PopulationRepository
) : ViewModel() {
    private var recyclerViewAdapter: ProductsAdapter = ProductsAdapter()

    val productsLiveData: LiveData<ArrayList<Product>>
        get() = repository.products

    fun getAdapter(): ProductsAdapter {
        return recyclerViewAdapter
    }

    fun setAdapterData(items: ArrayList<Product>) {
        recyclerViewAdapter.setDataList(items)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    suspend fun getProducts() {
        viewModelScope.launch {
            repository.getProducts()
        }
    }


}