package com.example.tictactoe

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        habal()
    }
    fun playhere(view: View)
    {
        var id=0
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
//        Toast.makeText(this,"id : $id" , Toast.LENGTH_SHORT).show()
        playgame(id , view as Button)
    }


    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var emptycells = ArrayList<Int>()

    fun habal() { for (i in 1..9) {emptycells.add(i)} }

    var activeplayer:Int = 1

    fun playgame(id:Int,view:Button)
    {
        val x = activeplayer
        if (activeplayer==1)
        {
            view.text="X"
            view.setTextColor(Color.BLACK)
            player1.add(id)
            activeplayer=2
            emptycells.remove(id)
            checkwinner(x)
            autoplay()
        }
        else
        {
            view.text="O"
            view.setTextColor(Color.BLUE)
            player2.add(id)
            activeplayer=1
            emptycells.remove(id)
            checkwinner(x)
        }
        view.isEnabled=false
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

    fun autoplay()
    {
        if((winner==-1) && (!emptycells.isEmpty()))
        {
            var r = emptycells.random()
            var id2: Button?
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


    fun reset(view: View) {for (i in 1..9)
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
}
