package com.swapnesh.cgpoc.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.swapnesh.cgpoc.doamin.repository.LoginRepository
import com.swapnesh.cgpoc.doamin.usecase.LoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi


import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class LoginViewModelTest {

  /*  @Mock
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var loginRepo: LoginUseCase
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()*/

   /* @Before
    fun initSetUp() {
        Dispatchers.setMain(testDispatcher)
        loginRepo = Mockito.mock(LoginUseCase::class.java)
        loginViewModel = LoginViewModel(loginRepo)
    }

    @Test
    fun onResponseReceived_checkFailedState_isError() {

        loginViewModel.loginUser("test.com", "1365hf")
        assert(false)
    }*/

}