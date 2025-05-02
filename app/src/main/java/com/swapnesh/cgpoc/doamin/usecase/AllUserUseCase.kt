package com.swapnesh.cgpoc.doamin.usecase


import com.swapnesh.cgpoc.data.model.AllUsersResponse
import com.swapnesh.cgpoc.data.model.User
import com.swapnesh.cgpoc.data.network.Resource
import com.swapnesh.cgpoc.doamin.repository.AllUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class AllUserUseCase @Inject constructor(private val allUserRepository: AllUserRepository) {
    suspend operator fun invoke(): Flow<Resource<AllUsersResponse>> {
        return allUserRepository.allUsers()
    }


}