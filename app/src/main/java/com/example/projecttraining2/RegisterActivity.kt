package com.example.projecttraining2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.example.projecttraining2.databinding.ActivityRegisterBinding
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.apply {
            hide()
        }

//        binding.username.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                val username = binding.username.text.toString()
//                if(!username.matches("/[^A-Za-z0-9]+/".toRegex())){
//                    binding.username.error = "Username hanya boleh menggunakan angka dan huruf!"
//                } else {
//                    binding.username.error = null
//                }
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
//                TODO("Not yet implemented")
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        binding.email.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                val email = binding.email.text.toString()
//                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                    binding.email.error = "Email tidak sesuai!"
//                } else {
//                    binding.email.error = null
//                }
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
//                TODO("Not yet implemented")
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        binding.password.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                val password = binding.password.text.toString()
//                val confirmPassword = binding.confirmpass.text.toString()
//                if(password.length < 8){
//                    binding.password.error = "Password kurang dari 8 karakter"
//                } else if (password != confirmPassword){
//                    binding.confirmpass.error = "Password tidak sama"
//                } else {
//                    binding.password.error = null
//                    binding.confirmpass.error = null
//                }
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
//                TODO("Not yet implemented")
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        binding.confirmpass.addTextChangedListener(object : TextWatcher{
//            override fun afterTextChanged(s: Editable?) {
//                val password = binding.password.text.toString()
//                val confirmPassword = binding.confirmpass.text.toString()
//               if (password != confirmPassword){
//                    binding.confirmpass.error = "Password tidak sama"
//                } else {
//                    binding.confirmpass.error = null
//                }
//            }
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, before: Int) {
//                TODO("Not yet implemented")
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                TODO("Not yet implemented")
//            }
//        })
//
//        binding.signup.setOnClickListener {
//            val errUsername = binding.username.error
//            val errPassword = binding.password.error
//            val errConfirmPassword = binding.confirmpass.error
//            val errEmail = binding.email.error
//
//            if (errUsername != null && errPassword != null && errConfirmPassword != null && errEmail != null){
//                startActivity(Intent(this, HomePageActivity::class.java))
//            }
//        }
    }

    fun login(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}