package com.mobile.micosecha.ui.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.micosecha.databinding.ActivityMainBinding
import androidx.databinding.DataBindingUtil
import com.mobile.micosecha.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.lifecycleOwner = this
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}