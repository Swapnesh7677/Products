package com.swapnesh.cgpoc.doamin.repository



import com.swapnesh.cgpoc.data.model.DeleteResponse
import com.swapnesh.cgpoc.data.model.ProductResponse
import com.swapnesh.cgpoc.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface ProductListRepository {
    suspend fun productList(): Flow<Resource<ProductResponse>>
    suspend fun deleteProduct(id: Int): Flow<Resource<DeleteResponse>>

}