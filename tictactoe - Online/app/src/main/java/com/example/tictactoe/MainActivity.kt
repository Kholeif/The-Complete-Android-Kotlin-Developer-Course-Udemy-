package com.example.tictactoe

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity()
{
    //database instance
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    var myEmail:String? = null
    var myUid:String? = null

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        habal()
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        var bundle:Bundle = intent.extras!!
        myEmail = bundle.getString("email")
        myUid = bundle.getString("uid")
        textView.setText("My email : $myEmail")

        IncommingCalls()
    }

    fun playhere(view: View)
    {
        var id:Int=0
        when (view.id)
        {
            R.id.button1 -> id=1
            R.id.button2 -> id=2
            R.id.button3 -> id=3
            R.id.button4 -> id=4
            R.id.button5 -> id=5
            R.id.button6 -> id=6
            R.id.button7 -> id=7
            R.id.button8 -> id=8
            R.id.button9 -> id=9
        }
//        playgame(id , view as Button)
        myRef.child("playonline").child(SessionID!!).child(id.toString()).setValue(myEmail!!.split("@")[0])
    }


    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptycells = ArrayList<Int>()

    fun habal() { for (i in 1..9) {emptycells.add(i)} }

    var activeplayer:Int = 1

    fun playgame(id:Int,view:Button)
    {
        if (activeplayer==1)
        {
            var x=activeplayer
            view.text="X"
            view.setTextColor(Color.BLACK)
            player1.add(id)
            activeplayer=2
            emptycells.remove(id)
            checkwinner(x)
        }
        else
        {
            var x=activeplayer
            view.text="O"
            view.setTextColor(Color.BLUE)
            player2.add(id)
            activeplayer=1
            emptycells.remove(id)
            checkwinner(x)
        }
//        view.isEnabled=false
    }

    var winner:Int=-1
    fun checkwinner(x:Int)
    {
        if (x==1)
        {
            if(player1.containsAll(listOf(1,2,3))||player1.containsAll(listOf(4,5,6))||player1.containsAll(listOf(7,8,9))||player1.containsAll(listOf(1,4,7))||player1.containsAll(listOf(2,5,8))||player1.containsAll(listOf(3,6,9))||player1.containsAll(listOf(1,5,9))||player1.containsAll(listOf(3,5,7)))
            {
                Toast.makeText(this,"winner is player 1" , Toast.LENGTH_LONG).show()
                winner=1
            }
        }
        else
        {
            if(player2.containsAll(listOf(1,2,3))||player2.containsAll(listOf(4,5,6))||player2.containsAll(listOf(7,8,9))||player2.containsAll(listOf(1,4,7))||player2.containsAll(listOf(2,5,8))||player2.containsAll(listOf(3,6,9))||player2.containsAll(listOf(1,5,9))||player2.containsAll(listOf(3,5,7)))
            {
                Toast.makeText(this,"winner is player 2" , Toast.LENGTH_LONG).show()
                winner=2
            }
        }
        if (winner!=-1)
        {
            for (i in emptycells)
            {
                var id: Button?
                when (i)
                {
                    1 -> id = button1
                    2 -> id = button2
                    3 -> id = button3
                    4 -> id = button4
                    5 -> id = button5
                    6 -> id = button6
                    7 -> id = button7
                    8 -> id = button8
                    else -> id = button9
                }
                id.isEnabled = false
            }
        }
    }

    fun autoplay(r:Int)
    {
        if((winner==-1) && (!emptycells.isEmpty()))
        {
            var id2: Button
            when (r)
            {
                1 -> id2 = button1
                2 -> id2 = button2
                3 -> id2 = button3
                4 -> id2 = button4
                5 -> id2 = button5
                6 -> id2 = button6
                7 -> id2 = button7
                8 -> id2 = button8
                else -> id2 = button9
            }
            playgame(r,id2)
        }
    }

    fun reset(view: View)
    {for (i in 1..9)
    {
        var id: Button?
        when (i)
        {
            1 -> id = button1
            2 -> id = button2
            3 -> id = button3
            4 -> id = button4
            5 -> id = button5
            6 -> id = button6
            7 -> id = button7
            8 -> id = button8
            else -> id = button9
        }
        id.text = ""
        id.isEnabled = true
    }
        player1.clear()
        player2.clear()
        emptycells.clear()
        habal()
        winner = -1
        activeplayer = 1
    }
















    var SessionID:String? = null
    var PlayerSympol:String? = null

    fun Request(view: View) {
        val player2_email = editTextTextPersonName.text.toString()
        myRef.child("users").child(player2_email.split("@")[0]).child("request").push().setValue(myEmail)
        playonline(myEmail!! , player2_email ,this)
        PlayerSympol = "X"
    }
    fun Accept(view: View) {
        val player2_email = editTextTextPersonName.text.toString()
        myRef.child("users").child(player2_email.split("@")[0]).child("request").push().setValue(myEmail)
        playonline(player2_email , myEmail!! ,this)
        PlayerSympol = "O"
    }




    fun playonline(email1: String , email2:String , context: Context) {
        SessionID=email1.split("@")[0]+email2.split("@")[0]
        myRef.child("playonline").removeValue()
        reset(button1)
        player1.clear()
        player2.clear()
        for (i in 1..9) {
            myRef.child("playonline").child(SessionID!!).child(i.toString())
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(p0: DataSnapshot) {
                        val td = p0.getValue(String::class.java)
                        if (td != null) {
                            if (td != myEmail!!.split("@")[0]) {
                                activeplayer = if (PlayerSympol === "X") 1 else 2
                            } else {
                                activeplayer = if (PlayerSympol === "X") 2 else 1
                            }
                            autoplay(i)
                        }
//                try {
//                    reset(button1) // ay view w 5alas keda keda mish hayesta3melo
//                    player1.clear()
//                    player2.clear()
//
//                    val td = p0.value as HashMap<String,String>
//                    var value:String
//                    for (key in td.keys){
//                        value = td[key]!!
//
//                        if(value!=myEmail!!.split("@")[0]){
//                            activeplayer = if (PlayerSympol==="X") 1 else 2
//                        }else{
//                            activeplayer = if (PlayerSympol==="X") 2 else 1
//                        }
//
//                        textView2.setText(key)
//                        textView3.setText(value)
//
//                        autoplay(key.toInt())
//                    }
//                }catch (ex:Exception){}
                    }

                    override fun onCancelled(p0: DatabaseError) {
                        var error = p0.message
                        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }


    var number = 0
    fun IncommingCalls(){
        myRef.child("users").child(myEmail!!.split("@")[0]).child("request").addValueEventListener(object:ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                try {
                    val td = p0.value as HashMap<String,Any>
                    if (td!=null){
                        var value:String?=null
                        for (key in td.keys){
                            value = td[key] as String
                            editTextTextPersonName.setText(value)
                            val notifyme = Notifications()
                            notifyme.Notify(applicationContext , value + " wants to play XO" , number)
                            number++
                            //hafady ba2y el requests
                            myRef.child("users").child(myEmail!!.split("@")[0]).child("request").setValue(true)
                            break
                        }
                    }
                }catch (ex:Exception){}
            }

            override fun onCancelled(p0: DatabaseError) {}
        })
    }
}
