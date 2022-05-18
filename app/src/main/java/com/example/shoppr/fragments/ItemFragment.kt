package com.example.shoppr.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.shoppr.R
import com.example.shoppr.logic.ShoppingItem


class ItemFragment : Fragment() {

    lateinit var item: ShoppingItem
    lateinit var shopName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Gets the arguments passed
        val bundle = arguments
        if (bundle != null) {
            val args = ItemFragmentArgs.fromBundle(bundle)
            item = args.item
            shopName = args.shopName
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

        //Updates the fragment with data from passed arguments
        val itemImage : ImageView =  view.findViewById(R.id.item_image)
        val itemName : TextView =  view.findViewById(R.id.item_name)
        val itemPrice : TextView =  view.findViewById(R.id.item_price)
        val itemCategory : TextView =  view.findViewById(R.id.item_category)
        val shopAvailability: TextView = view.findViewById(R.id.item_available)

        itemImage.setImageResource(item.image)
        itemName.text = item.name
        itemPrice.text = "£" + item.price.toString()

        if(item.category != null){
            itemCategory.text = item.category.toString().lowercase().capitalize()
        }
        shopAvailability.text = shopName

    }


}