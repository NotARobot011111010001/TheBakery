package com.example.shoppr.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shoppr.R
import com.example.shoppr.fragments.ShopItemsFragment
import com.example.shoppr.fragments.ShopsFragmentDirections
import com.example.shoppr.logic.Shop
import com.example.shoppr.logic.ShopManager
import com.example.shoppr.logic.ShoppingItem
import com.example.shoppr.util.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


class HomeActivity : AppCompatActivity() {

    private val tag = "HomeActivity"

    val shopManager = ShopManager()
    private val viewModel by viewModels<LoginViewModel>()
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        WindowCompat.getInsetsController(window, window.decorView)?.apply {
            isAppearanceLightStatusBars = true
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_bar)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_host_frag) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigationView.setupWithNavController(navController)

        viewModel.authenticationState.observe(this) { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.UNAUTHENTICATED -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> Log.d(
                    tag,
                    "Auth: Continue to homeActivity"
                )
            }
        }

    }

    fun openShop(shop: Shop){

        val action = ShopsFragmentDirections.actionShopsFragmentToShopItemsFragment(shop)
        navController.navigate(action)

    }

}