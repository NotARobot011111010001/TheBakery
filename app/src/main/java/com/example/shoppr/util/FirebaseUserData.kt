package com.example.shoppr.util

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class FirebaseUserData : LiveData<FirebaseUser?>() {
    private val authInstance = FirebaseAuth.getInstance()

    private val authListener = FirebaseAuth.AuthStateListener { authInstance ->
        value = authInstance.currentUser
    }

    override fun onActive() {
        authInstance.addAuthStateListener(authListener)
    }
    override fun onInactive() {
        authInstance.removeAuthStateListener(authListener)
    }
}