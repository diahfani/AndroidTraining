package com.example.projecttraining2.data.retrofit

import com.example.projecttraining2.data.response.FakeStoreAPIResponse
import com.example.projecttraining2.data.response.FakeStoreAPIResponseItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    fun getProducts(): retrofit2.Call<List<FakeStoreAPIResponseItem>>

    @GET("products/{id}")
    fun getProductsByID(@Path("id") id: String?): retrofit2.Call<FakeStoreAPIResponseItem>
}