package com.swapnesh.cgpoc.data.network
import com.swapnesh.cgpoc.data.model.AllUsersResponse
import com.swapnesh.cgpoc.data.model.DeleteResponse
import com.swapnesh.cgpoc.data.model.LoginResponse
import com.swapnesh.cgpoc.data.model.ProductResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("auth/login")
    suspend fun loginUser(@Body request: RequestBody): Response<LoginResponse>

    @GET("users")
    suspend fun getAllUsers(): Response<AllUsersResponse>

    @GET("products")
    suspend fun  getProductList(): Response<ProductResponse>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Int?): Response<DeleteResponse>



}