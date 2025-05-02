package com.swapnesh.cgpoc.data.network

import okhttp3.RequestBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) : BaseDataSource() {

    suspend fun loginUser(request: RequestBody) = getResult {
        apiService.loginUser(
            request = request
        )
    }

    suspend fun getAllUsers() = getResult { apiService.getAllUsers() }


    suspend fun  getProductList() = getResult { apiService.getProductList() }

    suspend fun deleteProduct(id: Int) = getResult {
        apiService.deleteProduct(id = id)
    }

}