package com.swapnesh.cgpoc.data.repository

import com.swapnesh.cgpoc.data.model.DeleteResponse
import com.swapnesh.cgpoc.data.model.ProductResponse
import com.swapnesh.cgpoc.data.network.RemoteDataSource
import com.swapnesh.cgpoc.data.network.Resource

import com.swapnesh.cgpoc.doamin.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductListRepositoryImpl(private val remoteDataSource: RemoteDataSource) : ProductListRepository {

    override suspend fun productList(): Flow<Resource<ProductResponse>> {
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.getProductList()
            emit(response)
        }
    }

    override suspend fun deleteProduct(id:Int): Flow<Resource<DeleteResponse>> {
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.deleteProduct(id)
            emit(response)
        }
    }

}