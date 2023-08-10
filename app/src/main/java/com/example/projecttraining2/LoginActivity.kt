package com.example.projecttraining2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.projecttraining2.data.IsLogin
import com.example.projecttraining2.data.UsersModel
import com.example.projecttraining2.databinding.ActivityLoginBinding
//import com.example.projecttraining2.wrapper.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log


class LoginActivity : AppCompatActivity() {

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding
    companion object{
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
//        loginViewModel = obtainViewModel(this)
//        user = intent.getParcelableExtra(EXTRA_LOGIN)
//        if (user!= null) {
//            user = Users()
//        }

        supportActionBar?.apply {
            hide()
        }

        val handler = Handler()
        handler.postDelayed(Runnable {
            val userPref = UserPreference(this)
            val login = userPref.getLogin()

            if (login.isLogin){
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }, 3500)

//        observeData()

        binding?.Loginbutton?.setOnClickListener {
            val inputUsername = binding?.username?.text.toString()
            val inputPassword = binding?.password?.text.toString()
            Log.d("LoginActivity", "inputusername : ${inputUsername}, inputpassword: ${inputPassword}")
            when {
                inputUsername.isEmpty() -> {
                    binding?.username?.error = "Username harus diisi"
                }
                inputPassword.isEmpty() -> {
                    binding?.password?.error = "Password harus diisi"
                }
                else -> {
                    val userPref = UserPreference(this)
                    val user = userPref.getUser()
                    Log.d("LoginActivity", "username: ${user.username}, password: ${user.password}")
                    if (inputUsername != user.username || inputPassword != user.password){
                        Toast.makeText(this, "Login atau password salah!", Toast.LENGTH_SHORT).show()
                    } else {
                        val userPreference = UserPreference(this)
                        val login = IsLogin()
                        login.isLogin = true
                        login.usernameLogin = inputUsername
                        userPreference.setLogin(login)
                        startActivity(Intent(this, HomeActivity::class.java))
                    }
//                    loginViewModel.getUser(inputUsername, inputPassword)
//                    val user = loginViewModel.getUser(inputUsername, inputPassword)
//                    if (user != null){
//                        startActivity(Intent(this, HomeActivity::class.java))
//                    }
                }
            }
//            if(inputUsername.toString() == ""){
//                binding?.username?.error = "Field ini harus diisi!"
//            } else if (inputPassword.toString() == ""){
//                binding?.password?.error = "Field ini harus diisi!"
//            } else {
//                startActivity(Intent(this, HomeActivity::class.java))
//            }
        }
    }

//    private fun observeData() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                loginViewModel.state.collect{
//                    when(it) {
//                        is Resource.Success -> {
//                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
//                            finish()
//                        }
//                        is Resource.Error -> {
//                            Toast.makeText(this@LoginActivity, it.message, Toast.LENGTH_SHORT).show()
//                        }
//                        else -> {}
//                    }
//                }
//            }
//        }
//    }

//    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
//        val factory = ViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
//    }

    fun register(view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
    }
}