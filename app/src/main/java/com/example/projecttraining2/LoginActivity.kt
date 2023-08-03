package com.example.projecttraining2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.projecttraining2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            hide()
        }
        binding.Loginbutton.setOnClickListener {
            val inputUsername = binding.username.text
            val inputPassword = binding.password.text
            if(inputUsername.toString() == ""){
                binding.username.error = "Field ini harus diisi!"
            } else if (inputPassword.toString() == ""){
                binding.password.error = "Field ini harus diisi!"
            } else {
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }
    }

    fun register(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}