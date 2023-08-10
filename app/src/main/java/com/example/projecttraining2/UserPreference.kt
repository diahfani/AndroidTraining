package com.example.projecttraining2

import android.content.Context
import com.example.projecttraining2.data.IsLogin
import com.example.projecttraining2.data.IsOnBoarding
import com.example.projecttraining2.data.UsersModel

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val PASSWORD = "password"
        private const val ISLOGIN = "isLogin"
        private const val USERNAMELOGIN = "usernamelogin"
        private const val ONBOARDHASSHOWN = "onboardShown"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val logins = context.getSharedPreferences("login_state", Context.MODE_PRIVATE)

    fun setUser(value: UsersModel) {
        val editor = preferences.edit()
        editor.putString(USERNAME, value.username)
        editor.putString(EMAIL, value.email)
        editor.putString(PASSWORD, value.password)

        editor.apply()
    }

    fun getUser(): UsersModel {
        val model = UsersModel()
        model.username = preferences.getString(USERNAME, "")
        model.email = preferences.getString(EMAIL, "")
        model.password = preferences.getString(PASSWORD, "")
//        model.phoneNumber = preferences.getString(PHONE_NUMBER, "")
//        model.isLove = preferences.getBoolean(LOVE_MU, false)

        return model
    }

    fun setLogin(value: IsLogin){
        val  editor = preferences.edit()
        editor.putString(USERNAMELOGIN, value.usernameLogin)
        editor.putBoolean(ISLOGIN, value.isLogin)
        editor.apply()
    }


    fun getLogin() : IsLogin {
        val login = IsLogin()
        login.usernameLogin = preferences.getString(USERNAMELOGIN, "")
        login.isLogin = preferences.getBoolean(ISLOGIN, false)
        return login
    }

    fun getOnboard() : Boolean {
        return preferences.getBoolean(ONBOARDHASSHOWN, false)
    }

    fun setOnboard(){
        val editor = preferences.edit()
        editor.putBoolean(ONBOARDHASSHOWN, true)
        editor.apply()
    }


    fun logout(){
        val editor = preferences.edit()
        editor.putString(USERNAMELOGIN, null)
        editor.putBoolean(ISLOGIN, false)
        editor.putBoolean(ONBOARDHASSHOWN, false)
        editor.apply()

    }

}