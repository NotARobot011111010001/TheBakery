package com.example.shoppr

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView


data class ShoppingItems (
    val name: String,
    val price : Double,
    val Image : Int)

class ItemsFragment : Fragment() {


    private val images: IntArray = intArrayOf(R.drawable.bread, R.drawable.carrots,R.drawable.sandwich,R.drawable.wine)
    private val names = arrayOf("Bread", "Carrots", "Sandwich", "Wine")
    private val prices = arrayOf(4.99, 2.45, 4.25, 10.99)

    private var gridAdapter: GridAdapater? = null
    private val shoppingItemsList : MutableList<ShoppingItems> = mutableListOf()
    private lateinit var gridView : GridView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gridView = view.findViewById(R.id.items_grid)

        for(index in names.indices){
            val item = ShoppingItems(names[index], prices[index], images[index])
            shoppingItemsList.add(item)
        }
        gridAdapter = GridAdapater(context,shoppingItemsList)
        gridView.adapter = gridAdapter

    }

    class GridAdapater(private val context: Context?, private val gridItems: MutableList<ShoppingItems>) : BaseAdapter() {

        private val filteredGridItems = gridItems

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

            image.setImageResource(filteredGridItems[position].Image)
            name.text = filteredGridItems[position].name
            price.text = filteredGridItems[position].price.toString()

            return convertView
        }

    }
}