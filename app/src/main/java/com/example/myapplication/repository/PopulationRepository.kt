package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.api.ApiInterface
import com.example.myapplication.model.Product
import com.example.myapplication.storage.ProductBD
import javax.inject.Inject

class PopulationRepository @Inject constructor(
    private val apiInterface: ApiInterface,
    private val populationBD: ProductBD
) {

    private val _products = MutableLiveData<ArrayList<Product>>()
    val products: LiveData<ArrayList<Product>>
        get() = _products

    suspend fun getProducts() {
        try {
            val productList = populationBD.getProductDao().getProducts()
            if (!productList.isEmpty()) {
                _products.postValue(productList as ArrayList<Product>?)
            }
            val result = apiInterface.getProducts(/*Constants.NATION, Constants.POPULATION*/)
            if (result.isSuccessful && result.body() != null) {
                populationBD.getProductDao().addProducts(result.body()!!)
                _products.postValue(result.body())
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            _products.postValue(getDBProducts()!!)
        }
    }

    private suspend fun getDBProducts(): ArrayList<Product> {
        return (populationBD.getProductDao().getProducts() as ArrayList<Product>)
    }
}