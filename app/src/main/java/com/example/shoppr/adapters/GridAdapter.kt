package com.example.shoppr.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.shoppr.R
import com.example.shoppr.logic.ShoppingItem
import java.util.ArrayList

class GridAdapter(private val context: Context?, private val gridItems: MutableList<Pair<String, ShoppingItem>>) : BaseAdapter(),
    Filterable {

    private var filteredGridItems = gridItems

    override fun getCount(): Int = filteredGridItems.size

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        convertView = LayoutInflater.from(context).inflate(R.layout.grid_item,parent, false)
        val image : ImageView = convertView.findViewById(R.id.item_image)
        val name : TextView = convertView.findViewById(R.id.item_name)
        val price : TextView = convertView.findViewById(R.id.item_price)
        val weight : TextView = convertView.findViewById(R.id.item_weight)
        val shopName: TextView = convertView.findViewById(R.id.shop_name)

        image.setImageResource(filteredGridItems[position].second.image)
        name.text = filteredGridItems[position].second.name
        price.text = "£" + filteredGridItems[position].second.price.toString()
        weight.text = filteredGridItems[position].second.weight
        shopName.text = filteredGridItems[position].first

        return convertView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchText = constraint.toString().lowercase()
                val filterResults = FilterResults()
                if(searchText.isEmpty()){
                    filterResults.count = gridItems.size
                    filterResults.values = gridItems
                }else{
                    val resultsList = ArrayList<Pair<String, ShoppingItem>>()
                    for (gridItem in gridItems){
                        if(gridItem.second.name.lowercase().contains(searchText) ||
                            gridItem.second.price.toString().contains(searchText) ||
                            gridItem.second.weight.lowercase().contains(searchText) ||
                            gridItem.first.lowercase().contains(searchText)){

                            resultsList.add(gridItem)
                        }
                    }
                    filterResults.count = resultsList.size
                    filterResults.values = resultsList

                }
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                filteredGridItems = filterResults.values as MutableList<Pair<String, ShoppingItem>>
                notifyDataSetChanged()
            }

        }
    }

}