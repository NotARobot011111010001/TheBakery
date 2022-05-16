package com.example.shoppr.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shoppr.R
import com.example.shoppr.logic.Category
import com.example.shoppr.logic.Shop
import com.example.shoppr.logic.ShoppingItem
import java.util.ArrayList

class ShopsListAdapter(private val context: Context?, private val shopsList: MutableList<Shop>) : BaseAdapter(),
    Filterable {

    private var filteredGridItems = shopsList

    override fun getCount(): Int = filteredGridItems.size

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        convertView = LayoutInflater.from(context).inflate(R.layout.advert_item,parent, false)
        val image : ImageView = convertView.findViewById(R.id.advert_image)
        val name : TextView = convertView.findViewById(R.id.advert_name)
        val slogan : TextView = convertView.findViewById(R.id.advert_slogan)

        image.setImageResource(filteredGridItems[position].backgroundImage)
        name.text = filteredGridItems[position].name
        slogan.text =  filteredGridItems[position].slogan


        return convertView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults? {
                val searchText = constraint.toString().lowercase()
                val filterResults = FilterResults()
                if(searchText.isEmpty()){
                    filterResults.count = shopsList.size
                    filterResults.values = shopsList
                }else{
                    val resultsList = ArrayList<Shop>()
                    for (shop in shopsList){
                        if(shop.name.lowercase().contains(searchText)){
                                resultsList.add(shop)
                        }
                    }
                    filterResults.count = resultsList.size
                    filterResults.values = resultsList

                }
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredGridItems = filterResults.values as MutableList<Shop>
                notifyDataSetChanged()
            }

        }
    }

}