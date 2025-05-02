package com.swapnesh.cgpoc.data.repository


import com.google.gson.Gson
import com.swapnesh.cgpoc.data.model.LoginResponse
import com.swapnesh.cgpoc.data.network.RemoteDataSource
import com.swapnesh.cgpoc.data.network.Resource
import com.swapnesh.cgpoc.doamin.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject


class LoginRepositoryImpl(private val remoteDataSource: RemoteDataSource) : LoginRepository {

    override suspend fun loginUser(username: String, password: String): Flow<Resource<LoginResponse>> {

        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("password", password)

        val body = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())
        return flow {
            emit(Resource.loading(null))
            val response = remoteDataSource.loginUser(body)
            emit(response)
        }
    }

}