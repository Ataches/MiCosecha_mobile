package com.mobile.micosecha.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.micosecha.databinding.ActivityMainBinding
import com.mobile.micosecha.ui.main.viewmodel.LoginViewModel
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mobile.micosecha.R
import com.mobile.micosecha.data.model.LoginUser

class MainActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.lifecycleOwner = this

        binding.loginViewModel = loginViewModel
        loginViewModel.user.observe(this, {
            /*if (binding.txtEmailAddress.text.toString().isEmpty() || binding.txtPassword.text.toString()
                    .isEmpty()
            ) {
                binding.txtEmailAddress.error = "Check your E-Mail Address or password"
                binding.txtEmailAddress.requestFocus()
            } else if (!it.isEmailValid) {
                binding.txtEmailAddress.error = "Enter a Valid E-mail Address"
                binding.txtEmailAddress.requestFocus()
            } else*/ if (!it.isPasswordLengthGreaterThan5) {
                binding.txtPassword.error = "Enter at least 6 Digit password"
                binding.txtPassword.requestFocus()
            } else {
                // TODO: send it to bd to check user
                startActivity(Intent(this, HomeActivity::class.java))
            }
        })
    }
}