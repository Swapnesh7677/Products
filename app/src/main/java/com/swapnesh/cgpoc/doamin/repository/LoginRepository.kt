package com.swapnesh.cgpoc.doamin.repository


import com.swapnesh.cgpoc.data.model.LoginResponse
import com.swapnesh.cgpoc.data.network.Resource
import kotlinx.coroutines.flow.Flow


interface LoginRepository {
    suspend fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>>
}