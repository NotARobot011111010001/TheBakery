package com.example.shoppr.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shoppr.R
import com.example.shoppr.activities.HomeActivity
import com.example.shoppr.adapters.ItemsGridAdapter
import com.example.shoppr.adapters.ShopsListAdapter
import com.example.shoppr.logic.Category
import com.example.shoppr.logic.FeaturedItem
import com.example.shoppr.logic.Shop
import com.example.shoppr.logic.ShopManager
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ShopItemsFragment : Fragment() {

    lateinit var shop: Shop
    private lateinit var gridView : GridView
    private lateinit var gridAdapter: ItemsGridAdapter
    private lateinit var shopManager : ShopManager
    private lateinit var listButton : List<Pair<CheckBox,Category>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = arguments
        if (bundle != null) {
            val args = ShopItemsFragmentArgs.fromBundle(bundle)
            shop = args.shop
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopManager = (activity as HomeActivity).shopManager
        gridView = view.findViewById(R.id.items_grid)
        gridAdapter = ItemsGridAdapter(context,shopManager.getItemsInShop(shop))
        gridView.adapter = gridAdapter

        val featuredImage : ImageView =  view.findViewById(R.id.advert_image)
        val featuredName : TextView =  view.findViewById(R.id.advert_name)
        val featuredSlogan : TextView =  view.findViewById(R.id.advert_slogan)

        featuredImage.setImageResource(shop.backgroundImage)
        featuredName.text = shop.name
        featuredSlogan.text = shop.slogan


        val searchView : androidx.appcompat.widget.SearchView = view.findViewById(R.id.items_search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                gridAdapter.filter.filter(newText)

                return true
            }

        })

        val listButton = listOf<Pair<CheckBox, Category>>(
            Pair(view.findViewById(R.id.category_button_1), Category.VEGETABLE),
            Pair(view.findViewById(R.id.category_button_2), Category.DAIRY),
            Pair(view.findViewById(R.id.category_button_3), Category.MEAT),
            Pair(view.findViewById(R.id.category_button_4), Category.DRINK),
            Pair(view.findViewById(R.id.category_button_5), Category.SNACK)
        )

        for (button in listButton) {
            if (button.first is CheckBox) {
                button.first.isChecked = false
                button.first.setOnClickListener {
                    for (x in listButton) {
                        if (x != button)
                            x.first.isChecked = false
                    }
                    if (button.first.isChecked) {
                        gridAdapter.currentCategory = button.second
                    } else {
                        gridAdapter.currentCategory = null
                    }
                    gridAdapter.filter.filter(searchView.query)

                }

            }

        }

    }
}
