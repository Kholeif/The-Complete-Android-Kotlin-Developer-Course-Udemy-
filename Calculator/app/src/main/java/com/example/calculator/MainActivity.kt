package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickevent(view: View) {
        var textshown = textView.text.toString()
//        if (textshown=="0"){textshown=""}
        if (newoperation){textshown=""}
        when (view.id){
            R.id.b0 -> textshown+="0"
            R.id.b1 -> textshown+="1"
            R.id.b2 -> textshown+="2"
            R.id.b3 -> textshown+="3"
            R.id.b4 -> textshown+="4"
            R.id.b5 -> textshown+="5"
            R.id.b6 -> textshown+="6"
            R.id.b7 -> textshown+="7"
            R.id.b8 -> textshown+="8"
            R.id.b9 -> textshown+="9"
            R.id.bdot -> textshown+="."
            R.id.bplusminus -> textshown="-"+textshown
            R.id.bac -> textshown="0"
        }
        textView.text = textshown
//        textView.setText(textshown)
    }
    var op="+"
    var number1=""
    var newoperation = false

    fun clickevent2(view: View) {
        number1 = textView.text.toString()
//        textView.text="0"
        newoperation=true
        when (view.id) {
            R.id.bplus -> op="+"
            R.id.bminus -> op="-"
            R.id.btimes -> op="*"
            R.id.bdiv -> op="/"
        }
    }

    fun clickevent3(view: View) {
        var number2 = textView.text.toString()
        var result:Double?=null
        when(op){
            "+" -> result=(number1.toDouble()+number2.toDouble())
            "-" -> result=(number1.toDouble()-number2.toDouble())
            "*" -> result=(number1.toDouble()*number2.toDouble())
            "/" -> result=(number1.toDouble()/number2.toDouble())
        }
        textView.text = result.toString()
        newoperation=false
    }
}
