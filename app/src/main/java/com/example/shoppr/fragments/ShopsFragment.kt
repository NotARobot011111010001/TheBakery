package com.example.shoppr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SearchView
import com.example.shoppr.R
import com.example.shoppr.activities.HomeActivity
import com.example.shoppr.adapters.ItemsGridAdapter
import com.example.shoppr.adapters.ShopsListAdapter
import com.example.shoppr.logic.ShopManager


class ShopsFragment : Fragment() {

    private lateinit var listView : ListView
    private lateinit var shopManager : ShopManager
    private lateinit var shopsAdapter: ShopsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_shops, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        shopManager = (activity as HomeActivity).shopManager
        listView = view.findViewById(R.id.shop_list)
        shopsAdapter = ShopsListAdapter(context, shopManager.shops)
        listView.adapter = shopsAdapter


        val searchView : androidx.appcompat.widget.SearchView = view.findViewById(R.id.shop_search)
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                shopsAdapter.filter.filter(newText)

                return true
            }

        })
    }


}