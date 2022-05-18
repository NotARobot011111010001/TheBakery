package com.example.shoppr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shoppr.R
import com.example.shoppr.logic.Shop
import com.example.shoppr.logic.ShopManager
import com.example.shoppr.logic.ShoppingItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ItemFragment : Fragment() {

    lateinit var item: ShoppingItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val args = ItemFragmentArgs.fromBundle(bundle)
            item = args.item
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val featuredImage : ImageView =  view.findViewById(R.id.item_name)
        val featuredName : TextView =  view.findViewById(R.id.advert_name)
        val featuredSlogan : TextView =  view.findViewById(R.id.advert_slogan)

        //featuredImage.setImageResource(shop.backgroundImage)
        //featuredName.text = shop.name
        //featuredSlogan.text = shop.slogan
    }


}