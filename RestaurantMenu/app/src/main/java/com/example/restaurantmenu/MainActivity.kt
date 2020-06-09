package com.example.restaurantmenu

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.food_ticket.view.*

class MainActivity : AppCompatActivity() {

    var foodlist=ArrayList<food>()
    var adapter:FoodAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        foodlist.add(food("Coffee","Coffee is a brewed drink prepared from roasted coffee beans, the seeds of berries from certain Coffea species. Once ripe, coffee berries are picked, processed, and dried. Dried coffee seeds (referred to as \"beans\") are roasted to varying degrees, depending on the desired flavor. Roasted beans are ground and then brewed with near-boiling water to produce the beverage known as coffee.",R.drawable.coffee_pot))
        foodlist.add(food("Espresso","Espresso is a coffee-making method of Italian origin, in which a small amount of nearly boiling water is forced under pressure (expressed) through finely-ground coffee beans.",R.drawable.espresso))
        foodlist.add(food("French fries","French fries are served hot, either soft or crispy, and are generally eaten as part of lunch or dinner or by themselves as a snack, and they commonly appear on the menus of diners, fast food restaurants, pubs, and bars. They are usually salted and, depending on the country, may be served with ketchup, vinegar, mayonnaise, tomato sauce, or other local specialties. Fries can be topped more heavily, as in the dishes of poutine or chili cheese fries. Chips can be made from kumara or other sweet potatoes instead of potatoes. A baked variant, oven chips, uses less oil or no oil. One very common fast food dish is fish and chips.",R.drawable.french_fries))
        foodlist.add(food("Honey","Honey is a sweet, viscous food substance made by honey bees and some related insects.[1] Bees produce honey from the sugary secretions of plants (floral nectar) or from secretions of other insects (such as honeydew), by regurgitation, enzymatic activity, and water evaporation. Bees store honey in wax structures called honeycombs. The variety of honey produced by honey bees (the genus Apis) is the best-known, due to its worldwide commercial production and human consumption. Honey is collected from wild bee colonies, or from hives of domesticated bees, a practice known as beekeeping or apiculture.",R.drawable.honey))
        foodlist.add(food("Strawberry ice cream","Strawberry ice cream is a flavor of ice cream made with strawberry or strawberry flavoring. It is made by blending in fresh strawberries or strawberry flavoring with the eggs, cream, vanilla and sugar used to make ice cream. Most strawberry ice cream is colored pink or light red. Strawberry ice cream dates back at least to 1813, when it was served at the second inauguration of James Madison. Along with vanilla and chocolate ice cream, strawberry is one of the three flavors in Neapolitan ice cream. Variations of strawberry ice cream include strawberry cheesecake ice cream and strawberry ripple ice cream, which is vanilla ice cream with a ribbon of strawberry jam or syrup. Some ice cream sandwiches are prepared neapolitan-style, and include strawberry ice cream.",R.drawable.strawberry_ice_cream))
        foodlist.add(food("Sugar cubes","Sugar cubes (sometimes called sugar lumps) are white or brown granulated sugars lightly steamed and pressed together in block shape. They are used to sweeten drinks.",R.drawable.sugar_cubes))

        adapter = FoodAdapter(this , foodlist)
        GridViewx.adapter = adapter
    }

    inner class FoodAdapter: BaseAdapter {
        var foodlist=ArrayList<food>()
        var context: Context?=null

        constructor(context: Context, foodlist:ArrayList<food>):super(){
            this.foodlist=foodlist
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            var foodexample = foodlist[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            var myview: View?=null
//            when(animal.iskiller){
//                false -> myview=inflator.inflate(R.layout.animal_ticket,null)
//                true -> myview=inflator.inflate(R.layout.killer_ticker,null)
//            }
        var myview=inflator.inflate(R.layout.food_ticket,null)
            myview.textView.text=foodexample.name!!
            myview.imageView.setImageResource(foodexample.image!!)
            myview.imageView.setOnClickListener{
                val ta7weel = Intent(context,FoodInfo::class.java)
                ta7weel.putExtra("name",foodexample.name!!)
                ta7weel.putExtra("des",foodexample.des!!)
                ta7weel.putExtra("image",foodexample.image!!)
                context!!.startActivity(ta7weel)
            }
            return  myview
        }

        override fun getItem(position: Int): Any {
            return foodlist[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return foodlist.size
        }

    }
}
