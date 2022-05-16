package com.example.shoppr.fragments

import android.content.Context
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shoppr.R
import com.example.shoppr.activities.HomeActivity
import com.example.shoppr.adapters.GridAdapter
import com.example.shoppr.logic.ShopManager
import com.example.shoppr.logic.ShoppingItem
import java.util.*


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
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                gridAdapter?.filter?.filter(newText)
                return true
            }

        })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        //shoppingItemsList.clear()
    }





}