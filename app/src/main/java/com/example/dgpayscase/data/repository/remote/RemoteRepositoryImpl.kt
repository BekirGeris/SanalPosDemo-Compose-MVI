package com.example.dgpayscase.data.repository.remote

import com.example.dgpayscase.model.Product
import com.example.dgpayscase.util.Constants
import com.example.dgpayscase.util.Resource
import com.example.dgpayscase.util.Response
import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

@ActivityScoped
class RemoteRepositoryImpl @Inject constructor(private val repository: RemoteRepository) {

    private suspend fun <T> generateFlow(function: suspend () -> Response<T>): Flow<Resource<T>> {
        return flow {
            emit(Resource.Loading())
            try {
                val response = function.invoke()
                if (response.success) {
                    emit(Resource.Success(Constants.Status.SUCCESS, response.message, response.data))
                } else {
                    emit(Resource.Error(Constants.Status.SUCCESS, response.message))
                }
            } catch (e: Exception) {
                if (e is HttpException) {
                    val response = e.response()
                    val errorBody = response?.errorBody()?.string() ?: "Unknown error occurred"
                    val errorMessage = try {
                        val errorJson = JSONObject(errorBody)
                        errorJson.getString("message")
                    } catch (ex: Exception) {
                        "Unknown error occurred"
                    }
                    emit(Resource.Error(Constants.Status.FAILED, errorMessage))
                } else {
                    emit(Resource.Error(Constants.Status.FAILED, "Error: ${e.localizedMessage}"))
                }
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun addProduct(product: Product): Flow<Response<Any>> {
        return generateFlow { repository.addProduct(product) }
    }

    suspend fun updateProduct(product: Product): Flow<Response<Any>> {
        return generateFlow { repository.updateProduct(product) }
    }

    suspend fun deleteProduct(product: Product): Flow<Response<Any>> {
        return generateFlow { repository.deleteProduct(product) }
    }

    suspend fun getProducts(): Flow<Response<ArrayList<Product>>> {
        return generateFlow { repository.getProducts() }
    }
}