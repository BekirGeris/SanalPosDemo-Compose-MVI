package com.example.dgpayscase.data.repository.remote

import com.example.dgpayscase.model.Product
import com.example.dgpayscase.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class RemoteRepositoryImpl @Inject constructor(private val repository: RemoteRepository) : RemoteRepository {

    override suspend fun addProduct(product: Product): Response<Any> {
        return repository.addProduct(product)
    }

    override suspend fun updateProduct(product: Product): Response<Any> {
        return repository.updateProduct(product)
    }

    override suspend fun deleteProduct(product: Product): Response<Any> {
        return repository.deleteProduct(product)
    }

    override suspend fun getProducts(): Response<ArrayList<Product>> {
        return repository.getProducts()
    }
}