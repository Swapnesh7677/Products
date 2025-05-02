package com.swapnesh.cgpoc.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swapnesh.cgpoc.data.model.AllUsersResponse
import com.swapnesh.cgpoc.data.model.User
import com.swapnesh.cgpoc.data.network.Status
import com.swapnesh.cgpoc.doamin.usecase.AllUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersViewModel @Inject constructor(
    private val allUserUseCase: AllUserUseCase
) : ViewModel() {

    val _users  = mutableStateOf<List<User>>(emptyList())
    val users : State<List<User>>  = _users

    private val _apiErrorToast = MutableLiveData<String>()
    val apiErrorToast: LiveData<String> = _apiErrorToast

    private val _waitForServer = MutableLiveData(false)
    val waitForServer: LiveData<Boolean> = _waitForServer

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            allUserUseCase().collect { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        if (resource.data != null) {
                            _users.value = resource.data.users
                        } else {
                            _waitForServer.postValue(false)
                        }
                    }

                    Status.LOADING -> {
                        _waitForServer.postValue(true)
                    }
                    Status.ERROR -> {
                        _waitForServer.postValue(false)
                    }
                }
            }
        }
    }




}