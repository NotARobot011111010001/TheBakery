package com.example.shoppr.logic

import android.location.Location
import android.os.Parcelable
import com.example.shoppr.R
import kotlinx.parcelize.Parcelize


class ShopManager {

    val shops: MutableList<Shop> = mutableListOf()

    init{


        //This is just dummy test data currently
        //This data would be filled from external SQL database in production

        val superMarketItemsList = mutableListOf<ShoppingItem>(
            ShoppingItem("Bread", "500g",4.99, Category.BAKED, R.drawable.bread),
            ShoppingItem("Carrots", "500g",4.99, Category.VEGETABLE, R.drawable.carrots),
            ShoppingItem("Sandwich", "120g", 4.25, Category.SNACK, R.drawable.sandwich),
            ShoppingItem("Wine", "750ml",10.99, Category.DRINK, R.drawable.wine),
            ShoppingItem("Eggs", "500g", 3.99, Category.DAIRY, R.drawable.eggs),
            ShoppingItem("Milk", "4L", 2.65, Category.DAIRY, R.drawable.milk),
            ShoppingItem("Yoghurt", "500g", 1.55, Category.DAIRY, R.drawable.yoghurt),
            ShoppingItem("Crisps", "300g", 1.15, Category.SNACK, R.drawable.crisps),
            ShoppingItem("Beans", "500g", 1.25, Category.VEGETABLE, R.drawable.beans)
        )

        val bakeryItemsList = mutableListOf<ShoppingItem>(
            ShoppingItem("Donuts", "500g", 4.99, Category.BAKED, R.drawable.donuts),
            ShoppingItem("Cupcake", "300g", 1.99, Category.BAKED, R.drawable.cupcake),
            ShoppingItem("Cake", "500g", 6.55, Category.BAKED, R.drawable.cake),
            ShoppingItem("Birthday Cake", "800g", 10.55, Category.BAKED, R.drawable.bdaycake),
            ShoppingItem("Croissant", "300g", 2.55, Category.BAKED, R.drawable.croissants),
            ShoppingItem("Bagels", "100g", 1.55, Category.BAKED, R.drawable.bagels),
            ShoppingItem("Baguette", "300g", 2.99, Category.BAKED, R.drawable.baguette),
            ShoppingItem("Cookies", "400g", 2.99, Category.BAKED, R.drawable.cookies)
        )

        val freshProduceItemsList = mutableListOf<ShoppingItem>(
            ShoppingItem("Carrots", "400g", 1.55, Category.VEGETABLE, R.drawable.carrots),
            ShoppingItem("Tomatoes", "1000g", 3.55, Category.VEGETABLE, R.drawable.tomatoes),
            ShoppingItem("Lettuce", "600g", 0.99, Category.VEGETABLE, R.drawable.lettuce),
            ShoppingItem("Potatoes", "1.5kg", 5.99, Category.VEGETABLE, R.drawable.potatoes),
            ShoppingItem("Lady Fingers", "500g", 1.99, Category.VEGETABLE, R.drawable.ladyfingers),
            ShoppingItem("Broccoli", "500g", 0.99, Category.VEGETABLE, R.drawable.broccoli),
            ShoppingItem("Spinach", "800g", 1.99, Category.VEGETABLE, R.drawable.spinach),
            ShoppingItem("Cucumbers", "200g", 1.99, Category.VEGETABLE, R.drawable.cucumbers),
            ShoppingItem("Bell Peppers", "500g", 1.99, Category.VEGETABLE, R.drawable.bellpepers)
        )

        val featuredCoke = FeaturedItem("Coca Cola","Only 99p per can", R.drawable.feautedcoke,0.99)
        val featuredBread = FeaturedItem("Wholegrain bread","Now only £4.99", R.drawable.featuredbread,4.99)

        shops.add(Shop("Super SuperMarket", "The country's best supermarket",
            Location("blank"), R.drawable.super_supermarket, superMarketItemsList ,
            mutableListOf(featuredCoke)))

        shops.add(Shop("The Bakery", "Get a taste of the finest bread",
            Location("blank"), R.drawable.the_bakery, bakeryItemsList ,
            mutableListOf(featuredBread)))

        shops.add(Shop("FreshProduce", "Your local market for fresh product",
            Location("blank"), R.drawable.fresh_produce, freshProduceItemsList ,
            mutableListOf()))

    }

    //Gets a random featured item from any shop
    fun getRandomFeatured(): FeaturedItem{
        val featuredItems : MutableList<FeaturedItem> = mutableListOf()
        for(shop in shops){
            featuredItems.addAll(shop.featuredItems)
        }
        return featuredItems.random()
    }

    //Gets all the items from a specific shop
    fun getItemsInShop(shop: Shop): MutableList<Pair<String, ShoppingItem>>{
        val listAll: MutableList<Pair<String, ShoppingItem>> = mutableListOf()
        for(item in shop.items){
            listAll.add(Pair(shop.name,item))
        }
        return listAll
    }

    //Gets a list of all items within a shop and also the shop they each belong to
    fun getItemsAndShop(): MutableList<Pair<String, ShoppingItem>> {
        val listAll: MutableList<Pair<String, ShoppingItem>> = mutableListOf()
        for (shop in shops){
            for(item in shop.items){
                listAll.add(Pair(shop.name,item))
            }

        }
        return listAll
    }


}


enum class Category{
    VEGETABLE, DAIRY, MEAT, DRINK, SNACK, BAKED
}

@Parcelize
data class Shop (
    val name: String,
    val slogan : String,
    val storeLocation: Location,
    val backgroundImage : Int,
    val items: List<ShoppingItem>,
    val featuredItems: List<FeaturedItem>) : Parcelable

@Parcelize
data class ShoppingItem (
    val name: String,
    val weight: String,
    val price : Double,
    val category: Category?,
    val image : Int): Parcelable

@Parcelize
data class FeaturedItem (
    val name: String,
    val slogan: String,
    val backgroundImage: Int,
    val price: Double
        ) : Parcelable
