package com.example.zooapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity()
{
    var listofanimals = ArrayList<Animal>()
    var adapter:AnimalAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listofanimals.add(Animal("Baboon","baboon lives in big places with trees",R.drawable.baboon,false))
        listofanimals.add(Animal("Bulldog","Bulldog lives in big places with trees",R.drawable.bulldog,true))
        listofanimals.add(Animal("Panda","Panda lives in big places with trees",R.drawable.panda,true))
        listofanimals.add(Animal("Swallow bird","Swallow bird lives in big places with trees",R.drawable.swallow_bird,false))
        listofanimals.add(Animal("White Tiger","White Tiger lives in big places with trees",R.drawable.white_tiger,false))
        listofanimals.add(Animal("Zebra","Zebra lives in big places with trees",R.drawable.zebra,false))
        listofanimals.add(Animal("Baboon","baboon lives in big places with trees",R.drawable.baboon,false))
        listofanimals.add(Animal("Bulldog","Bulldog lives in big places with trees",R.drawable.bulldog,true))
        listofanimals.add(Animal("Panda","Panda lives in big places with trees",R.drawable.panda,true))
        listofanimals.add(Animal("Swallow bird","Swallow bird lives in big places with trees",R.drawable.swallow_bird,false))
        listofanimals.add(Animal("White Tiger","White Tiger lives in big places with trees",R.drawable.white_tiger,false))
        listofanimals.add(Animal("Zebra","Zebra lives in big places with trees",R.drawable.zebra,false))

        adapter = AnimalAdapter(this , listofanimals)
        listview.adapter = adapter
    }

    fun delete (index:Int)
    {
        listofanimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }
    inner class AnimalAdapter: BaseAdapter {
        var listofanimals = ArrayList<Animal>()
        var context: Context?=null

        constructor(context: Context, listofanimals:ArrayList<Animal>):super(){
            this.listofanimals=listofanimals
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            var animal = listofanimals[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myview:View?=null
            when(animal.iskiller){
                false -> myview=inflator.inflate(R.layout.animal_ticket,null)
                true -> myview=inflator.inflate(R.layout.killer_ticker,null)
            }
//        var myview=inflator.inflate(R.layout.animal_ticket,null)
            myview.textView.text=animal.name!!
            myview.textView2.text=animal.des!!
            myview.imageView.setImageResource(animal.image!!)
        myview.imageView.setOnClickListener{
            delete(position)
//            val ta7weel = Intent(context,AnimalInfo::class.java)
//            ta7weel.putExtra("name",animal.name!!)
//            ta7weel.putExtra("des",animal.des!!)
//            ta7weel.putExtra("image",animal.image!!)
//            context!!.startActivity(ta7weel)
        }
            return  myview
        }

        override fun getItem(position: Int): Any {
            return listofanimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listofanimals.size
        }

    }
}
