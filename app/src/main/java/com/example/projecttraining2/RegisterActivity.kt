package com.example.projecttraining2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.projecttraining2.data.UsersModel
import com.example.projecttraining2.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class RegisterActivity : AppCompatActivity() {

    private var _binding: ActivityRegisterBinding? = null
    private lateinit var userModel: UsersModel
//    private var user: Users? = null
    private val binding get() = _binding
    companion object {
        const val EXTRA_USER = "extra_user"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding?.root)

//        userModel = intent.getParcelableExtra<UsersModel>("USER") as UsersModel

//        registerViewModel = obtainViewModel(this)
//        user = intent.getParcelableExtra(EXTRA_USER)
//        if (user != null){
//            isEdit = true
//        } else {
//            user = Users()
//        }

        binding?.signup?.setOnClickListener {
            val username = binding?.username?.text.toString().trim()
            val email = binding?.email?.text.toString()
            val password = binding?.password?.text.toString()
            val confirmPass = binding?.confirmpass?.text.toString()
            when {
                confirmPass != password -> {
                    binding?.confirmpass?.error = "Password tidak sama"
                }
                username.isEmpty() -> {
                    binding?.username?.error = "Username tidak boleh kosong"
                }
                email.isEmpty() -> {
                    binding?.email?.error = "Email tidak boleh kosong"
                }
                else -> {
                    saveUser(username, email, password)
                    finish()
                }

            }
        }

        supportActionBar?.apply {
            hide()
        }

    }

    private fun saveUser(username: String, email: String, password: String) {
        val userPreference = UserPreference(this)
        val user = UsersModel()
        user.username = username
        user.email = email
        user.password = password

        userPreference.setUser(user)
        Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show()
    }

//    private fun obtainViewModel(activity: AppCompatActivity): RegisterViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(RegisterViewModel::class.java)
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun login(view: View){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}