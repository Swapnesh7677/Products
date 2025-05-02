package com.swapnesh.cgpoc.di

import com.swapnesh.cgpoc.data.network.ApiService
import com.swapnesh.cgpoc.data.network.AuthInterceptor
import com.swapnesh.cgpoc.data.network.RemoteDataSource
import com.swapnesh.cgpoc.data.repository.AllUserRepositoryImpl
import com.swapnesh.cgpoc.data.repository.LoginRepositoryImpl
import com.swapnesh.cgpoc.data.repository.ProductListRepositoryImpl
import com.swapnesh.cgpoc.doamin.repository.AllUserRepository
import com.swapnesh.cgpoc.doamin.repository.LoginRepository
import com.swapnesh.cgpoc.doamin.repository.ProductListRepository
import com.swapnesh.cgpoc.utils.extension.BASE_URL
import com.swapnesh.cgpoc.utils.helper.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(AuthInterceptor())
        }.build()
    }

    @Provides
    @Singleton
    fun provideApi(okHttpClient: OkHttpClient): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: RemoteDataSource): LoginRepository {
        return LoginRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideProductRepository(api: RemoteDataSource): ProductListRepository {
        return ProductListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUsersRepository(api: RemoteDataSource): AllUserRepository {
        return AllUserRepositoryImpl(api)
    }
}


