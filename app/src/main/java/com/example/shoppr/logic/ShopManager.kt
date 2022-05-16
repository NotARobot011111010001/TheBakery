package com.example.shoppr.logic

import android.location.Location
import com.example.shoppr.R


class ShopManager {

    val shops: MutableList<Shop> = mutableListOf()

    init{


        //Super Supermarket filled with dummy data
        //This data would be filled from external SQL database in production
        var images: IntArray = intArrayOf(R.drawable.bread, R.drawable.carrots, R.drawable.sandwich, R.drawable.wine)
        var names = arrayOf("Bread", "Carrots", "Sandwich", "Wine")
        var weights = arrayOf("500g", "500g", "120g", "750ml")
        var prices = arrayOf(4.99, 2.45, 4.25, 10.99)
        var categories = arrayOf(null, Category.VEGETABLE, Category.SNACK, Category.DRINK)
        var shoppingItemsList : MutableList<ShoppingItem> = mutableListOf()
        for (index in names.indices) {
            val item = ShoppingItem(names[index], weights[index], prices[index], categories[index], images[index])
            shoppingItemsList.add(item)
        }

        var featuredCoke = FeaturedItem("Coca Cola","Only 99p per can", R.drawable.feautedcoke,0.99)
        var featuredBread = FeaturedItem("Wholegrain bread","Now only £4.99", R.drawable.featuredbread,4.99)
        shops.add(Shop("Super SuperMarket", "The country's best supermarket",
            Location("blank"), R.drawable.super_supermarket, shoppingItemsList,
            mutableListOf(featuredCoke)))

        shops.add(Shop("The Bakery", "Get a taste of the finest bread",
            Location("blank"), R.drawable.the_bakery, shoppingItemsList,
            mutableListOf(featuredBread)))

        shops.add(Shop("FreshProduce", "Your local market for fresh product",
            Location("blank"), R.drawable.fresh_produce, shoppingItemsList,
            mutableListOf()))

    }

    fun getRandomFeatured(): FeaturedItem{
        var featuredItems : MutableList<FeaturedItem> = mutableListOf()
        for(shop in shops){
            featuredItems.addAll(shop.featuredItems)
        }
        return featuredItems.random()
    }

    fun getItemsAndShop(): MutableList<Pair<String, ShoppingItem>> {
        var listAll: MutableList<Pair<String, ShoppingItem>> = mutableListOf()
        for (shop in shops){
            for(item in shop.items){
                listAll.add(Pair(shop.name,item))
            }

        }
        return listAll
    }


}


enum class Category{
    VEGETABLE, DAIRY, MEAT, DRINK, SNACK
}

data class Shop (
    val name: String,
    val slogan : String,
    val storeLocation: Location,
    val backgroundImage : Int,
    val items: List<ShoppingItem>,
    val featuredItems: List<FeaturedItem>)

data class ShoppingItem (
    val name: String,
    val weight: String,
    val price : Double,
    val category: Category?,
    val image : Int)

data class FeaturedItem (
    val name: String,
    val slogan: String,
    val backgroundImage: Int,
    val price: Double
        )
