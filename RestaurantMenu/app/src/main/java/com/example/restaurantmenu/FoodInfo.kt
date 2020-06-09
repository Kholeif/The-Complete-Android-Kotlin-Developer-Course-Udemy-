package com.example.restaurantmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_food_info.*

class FoodInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_info)

        var bundle:Bundle = intent.extras!!
        val name = bundle.getString("name")
        val des = bundle.getString("des")
        val image = bundle.getInt("image")

        imageView2.setImageResource(image)
        textView2.setText(name)
        textView3.setText(des)
    }
}
