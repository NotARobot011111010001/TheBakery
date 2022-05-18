package com.example.shoppr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppr.R
import com.example.shoppr.logic.Shop
import com.example.shoppr.logic.ShopManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


private const val CURRENT_SHOP = "currentShop"


/**
 * A simple [Fragment] subclass.
 * Use the [ItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ItemFragment : Fragment() {

    private var shop: Shop? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val value =  it.getString(CURRENT_SHOP)
            shop = Gson().fromJson(value!!, object : TypeToken<Shop>(){}.type)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance(shop: Shop?) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    val json = Gson().toJson(shop, object : TypeToken<Shop>(){}.type)
                    putString(CURRENT_SHOP, json)
                }
            }
    }
}