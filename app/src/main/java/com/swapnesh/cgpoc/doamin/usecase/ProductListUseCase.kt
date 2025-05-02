package com.swapnesh.cgpoc.doamin.usecase

import com.swapnesh.cgpoc.data.model.DeleteResponse
import com.swapnesh.cgpoc.data.model.ProductResponse
import com.swapnesh.cgpoc.data.network.Resource

import com.swapnesh.cgpoc.doamin.repository.ProductListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class ProductListUseCase @Inject constructor(private val productListRepository: ProductListRepository) {
    suspend operator fun invoke(): Flow<Resource<ProductResponse>> {
        return productListRepository.productList()
    }

    suspend operator fun invoke(id:Int): Flow<Resource<DeleteResponse>> {
        return productListRepository.deleteProduct(id)
    }
}