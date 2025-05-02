package com.swapnesh.cgpoc.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.swapnesh.cgpoc.R
import com.swapnesh.cgpoc.databinding.ActivityLoginBinding
import com.swapnesh.cgpoc.presentation.viewmodel.LoginViewModel
import com.swapnesh.cgpoc.utils.extension.beGone
import com.swapnesh.cgpoc.utils.extension.beVisible
import com.swapnesh.cgpoc.utils.extension.isInternetAvailable
import com.swapnesh.cgpoc.utils.extension.toast
import com.swapnesh.cgpoc.utils.helper.PreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()
    val prefs: PreferenceHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        binding.loginBtn.setOnClickListener {
            if (isValidate()) {
                loginViewModel.loginUser(
                    binding.etUsername.text.toString(), binding.etPassword.text.toString()
                )
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            loginViewModel.loginUserDataFlow.collect { data ->
                prefs.authToken = data.token.toString()
                toast("Logged in")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }
    private fun init() {
        loginViewModel._wating.value = false
        loginViewModel.waitForServer.observe(this) {
            if (it) {
                binding.progressBar.beVisible()
                binding.loginBtn.isEnabled = false
            } else {
                binding.progressBar.beGone()
                binding.loginBtn.isEnabled = true
            }
        }
        loginViewModel.apiErrorToast.observe(this) { error ->
            toast(error.toString())
        }
    }

    private fun isValidate(): Boolean {
        if (binding.etUsername.text!!.isEmpty() || binding.etUsername.text!!.isBlank()) {
            toast(getString(R.string.enter_username))
            return false
        }else if (binding.etPassword.text!!.isEmpty() || binding.etPassword.text!!.isBlank()) {
            toast(getString(R.string.enter_password))
            return false
        } else if (binding.etPassword.text!!.length < 8) {
            toast(getString(R.string.password_too_short))
            return false
        } else if (!isInternetAvailable()) {
            toast(getString(R.string.internet_not_available))
            return false
        } else {
            return true
        }
    }

}