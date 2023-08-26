package com.example.dgpayscase.data.repository.remote

import com.example.dgpayscase.model.Product
import com.example.dgpayscase.util.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RemoteRepository {
    @POST("addProduct")
    suspend fun addProduct(product: Product) : Response<Any>

    @POST("updateProduct")
    suspend fun updateProduct(product: Product) : Response<Any>

    @POST("deleteProduct")
    suspend fun deleteProduct(product: Product) : Response<Any>

    @GET("products")
    suspend fun getProducts() : Response<ArrayList<Product>>
}