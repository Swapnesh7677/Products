package com.swapnesh.cgpoc.data.repository


import com.swapnesh.cgpoc.data.model.AllUsersResponse
import com.swapnesh.cgpoc.data.network.RemoteDataSource
import com.swapnesh.cgpoc.data.network.Resource
import com.swapnesh.cgpoc.doamin.repository.AllUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AllUserRepositoryImpl(private val remoteDataSource: RemoteDataSource) : AllUserRepository {

    override suspend fun allUsers(): Flow<Resource<AllUsersResponse>> {
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.getAllUsers()
            emit(response)
        }
    }

}