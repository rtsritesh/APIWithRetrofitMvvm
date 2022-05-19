package com.example.myapplication.api

import com.example.myapplication.model.Product
import com.example.myapplication.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET(Constants.PRODUCTS)
    suspend fun getProducts(
    ): Response<ArrayList<Product>>
}