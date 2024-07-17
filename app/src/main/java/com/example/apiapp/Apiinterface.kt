package com.example.apiapp

import MyData
import retrofit2.http.GET
import retrofit2.Call


interface Apiinterface {
    @GET("products")
    fun getProductData():Call<MyData>
}