package com.example.shoppr.util


import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

/**
 * Code used from:
 * https://developer.android.com/codelabs/advanced-android-kotlin-training-login#0
 **/

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED, UNAUTHENTICATED
    }

    val authenticationState = FirebaseUserData().map { user ->
        if (user != null) {
            AuthenticationState.AUTHENTICATED
        } else {
            AuthenticationState.UNAUTHENTICATED
        }
    }

}
