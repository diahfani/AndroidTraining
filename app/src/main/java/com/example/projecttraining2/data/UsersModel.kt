package com.example.projecttraining2.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersModel (
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
) : Parcelable

@Parcelize
data class IsLogin (
    var usernameLogin: String? = null,
    var isLogin: Boolean = false
        ) : Parcelable

@Parcelize
data class IsOnBoarding(
    var isFirstTime: Boolean = true
)  : Parcelable