package com.example.shoppr.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.shoppr.R
import com.example.shoppr.activities.HomeActivity
import com.example.shoppr.adapters.GridAdapter
import com.example.shoppr.logic.Category
import com.example.shoppr.logic.ShopManager


class ItemsFragment : Fragment() {


    private lateinit var gridView : GridView
    private lateinit var gridAdapter: GridAdapter
    private lateinit var shopManager : ShopManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopManager = (activity as HomeActivity).shopManager
        gridView = view.findViewById(R.id.items_grid)
        gridAdapter = GridAdapter(context,shopManager.getItemsAndShop())
        gridView.adapter = gridAdapter

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

        val listButton = listOf<Pair<CheckBox,Category>>(
            Pair(view.findViewById(R.id.category_button_1), Category.VEGETABLE),
            Pair(view.findViewById(R.id.category_button_2),Category.DAIRY),
            Pair(view.findViewById(R.id.category_button_3),Category.MEAT),
            Pair(view.findViewById(R.id.category_button_4),Category.DRINK),
            Pair(view.findViewById(R.id.category_button_5),Category.SNACK)
        )
        for(button in listButton){
            if(button.first is CheckBox){
                button.first.setOnClickListener{
                    for (x in listButton) {
                        if (x != button)
                            x.first.isChecked = false
                    }
                    if (button.first.isChecked){
                        gridAdapter.currentCategory = button.second
                    }else{
                        gridAdapter.currentCategory = null
                    }
                    gridAdapter.filter.filter(searchView.query)

                }

            }

        }





    }







}