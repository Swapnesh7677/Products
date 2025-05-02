package com.swapnesh.cgpoc.doamin.repository

import com.swapnesh.cgpoc.data.model.AllUsersResponse
import com.swapnesh.cgpoc.data.network.Resource
import kotlinx.coroutines.flow.Flow



interface AllUserRepository {
    suspend fun allUsers(): Flow<Resource<AllUsersResponse>>
}