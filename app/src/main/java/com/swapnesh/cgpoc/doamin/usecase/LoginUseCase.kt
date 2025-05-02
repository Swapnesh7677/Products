package com.swapnesh.cgpoc.doamin.usecase


import com.swapnesh.cgpoc.data.model.LoginResponse
import com.swapnesh.cgpoc.data.network.Resource
import com.swapnesh.cgpoc.doamin.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


// An interactor class that executes the implementation of LoginViewModel


class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(username: String, pass: String): Flow<Resource<LoginResponse>> {
        return loginRepository.loginUser(username, pass)
    }
}