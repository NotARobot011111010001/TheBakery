package com.example.shoppr

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*


class ItemsFragment : Fragment() {


    private val images: IntArray = intArrayOf(R.drawable.bread, R.drawable.carrots,R.drawable.sandwich,R.drawable.wine)
    private val names = arrayOf("Bread", "Carrots", "Sandwich", "Wine")
    private val weights = arrayOf("500g", "500g", "120g", "750ml")
    private val prices = arrayOf(4.99, 2.45, 4.25, 10.99)

    private var gridAdapter: GridAdapter? = null
    private val shoppingItemsList : MutableList<ShoppingItems> = mutableListOf()
    private lateinit var gridView : GridView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridView = view.findViewById(R.id.items_grid)


        for(extra in 0..5) {
            for (index in names.indices) {
                val item = ShoppingItems(names[index], prices[index], weights[index], images[index])
                shoppingItemsList.add(item)
            }
        }

        gridAdapter = GridAdapter(context,shoppingItemsList)
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
        shoppingItemsList.clear()
    }

    data class ShoppingItems (
        val name: String,
        val price : Double,
        val weight: String,
        val Image : Int)


    class GridAdapter(private val context: Context?, private val gridItems: MutableList<ShoppingItems>) : BaseAdapter(),Filterable {

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

            image.setImageResource(filteredGridItems[position].Image)
            name.text = filteredGridItems[position].name
            price.text = "£" + filteredGridItems[position].price.toString()
            weight.text = filteredGridItems[position].weight

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
                        val resultsList = ArrayList<ShoppingItems>()
                        for (gridItem in gridItems){
                            if(gridItem.name.lowercase().contains(searchText) ||
                                gridItem.price.toString().contains(searchText) ||
                                gridItem.weight.lowercase().contains(searchText)){

                                resultsList.add(gridItem)
                            }
                        }
                        filterResults.count = resultsList.size
                        filterResults.values = resultsList

                    }
                    return filterResults
                }

                override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
                    filteredGridItems = filterResults.values as MutableList<ShoppingItems>
                    notifyDataSetChanged()
                }

            }
        }

    }
}