package com.example.mynotes

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var noteslist = ArrayList<note>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteslist.add(note(1,"Messi","Lionel Andrés Messi Cuccittini[note 1] (Spanish pronunciation: [ljoˈnel anˈdɾez ˈmesi] (About this soundlisten);[A] born 24 June 1987) is an Argentine professional footballer who plays as a forward and captains both Spanish club Barcelona and the Argentina national team. Often considered the best player in the world and widely regarded as one of the greatest players of all time, Messi has won a record six Ballon d'Or awards,[note 2] and a record six European Golden Shoes. He has spent his entire professional career with Barcelona, where he has won a club-record 34 trophies, including ten La Liga titles, four UEFA Champions League titles and six Copas del Rey. A prolific goalscorer and a creative playmaker, Messi holds the records for most goals in La Liga (438), a La Liga and European league season (50), most hat-tricks in La Liga (36) and the UEFA Champions League (8), and most assists in La Liga (181) and the Copa América (12). He has scored over 700 senior career goals for club and country."))
        noteslist.add(note(2,"Early life","Born and raised in central Argentina, Messi relocated to Spain to join Barcelona at age 13, for whom he made his competitive debut aged 17 in October 2004. He established himself as an integral player for the club within the next three years, and in his first uninterrupted season in 2008–09 he helped Barcelona achieve the first treble in Spanish football; that year, aged 22, Messi won his first Ballon d'Or. Three successful seasons followed, with Messi winning four consecutive Ballons d'Or, making him the first player to win the award four times and in a row.[9] During the 2011–12 season, he set the La Liga and European records for most goals scored in a single season, while establishing himself as Barcelona's all-time top scorer. The following two seasons, Messi finished second for the Ballon d'Or behind Cristiano Ronaldo (his perceived career rival), before regaining his best form during the 2014–15 campaign, becoming the all-time top scorer in La Liga and leading Barcelona to a historic second treble, after which he was awarded a fifth Ballon d'Or in 2015. Messi assumed the captaincy of Barcelona in 2018, and in 2019 he secured a record sixth Ballon d'Or."))
        noteslist.add(note(3,"Argentine","An Argentine international, Messi is his country's all-time leading goalscorer. At youth level, he won the 2005 FIFA World Youth Championship, finishing the tournament with both the Golden Ball and Golden Shoe, and an Olympic gold medal at the 2008 Summer Olympics. His style of play as a diminutive, left-footed dribbler drew comparisons with his compatriot Diego Maradona, who described Messi as his successor. After his senior debut in August 2005, Messi became the youngest Argentine to play and score in a FIFA World Cup during the 2006 edition, and reached the final of the 2007 Copa América, where he was named young player of the tournament. As the squad's captain from August 2011, he led Argentina to three consecutive finals: the 2014 FIFA World Cup, for which he won the Golden Ball, and the 2015 and 2016 Copas América. After announcing his international retirement in 2016, he reversed his decision and led his country to qualification for the 2018 FIFA World Cup, and a third-place finish at the 2019 Copa América."))

//        var adapter = NotesAdapter(this , noteslist)
//        listview.adapter = adapter
        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()

        //Load from DB
        LoadQuery("%")
    }
    override  fun onResume() {
        super.onResume()
        LoadQuery("%")
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()

        Toast.makeText(this,"onPause",Toast.LENGTH_LONG).show()
    }

    override fun onStop() {
        super.onStop()

        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show()
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show()
    }


    fun LoadQuery(title:String){

        var dbManager=DbManager(this)
        val projections= arrayOf("ID","Title","Description")
        val selectionArgs= arrayOf(title)
        val cursor=dbManager.Query(projections,"Title like ?",selectionArgs,"Title")
        noteslist.clear()
        if(cursor.moveToFirst()){

            do{
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val Title=cursor.getString(cursor.getColumnIndex("Title"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))

                noteslist.add(note(ID,Title,Description))

            }while (cursor.moveToNext())
        }

        var myNotesAdapter= NotesAdapter(this, noteslist)
        listview.adapter=myNotesAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val sv:SearchView = menu.findItem(R.id.SEARCH).actionView as SearchView
        val sm= getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                LoadQuery("%"+ query +"%")
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when(item.itemId){
                R.id.ADDNEWNOTE->{
                    //Got to add page
                    var intent= Intent(this,AddNotes::class.java)
                    startActivity(intent)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class NotesAdapter: BaseAdapter {
        var notelists = ArrayList<note>()
        var context: Context?=null

        constructor(context: Context, notelists:ArrayList<note>):super(){
            this.notelists=notelists
            this.context=context
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            var mynote = notelists[position]
            var myview=layoutInflater.inflate(R.layout.ticket,null)
            myview.textView.text=mynote.title!!
            myview.textView2.text=mynote.des!!
            myview.textView2.setOnClickListener{
                val ta7weel = Intent(context,NoteInfo::class.java)
                ta7weel.putExtra("title",mynote.title!!)
                ta7weel.putExtra("des",mynote.des!!)
                context!!.startActivity(ta7weel)
            }
            myview.imageView2.setOnClickListener( View.OnClickListener {
                var dbManager=DbManager(this.context!!)
                val selectionArgs= arrayOf(mynote.id.toString())
                dbManager.Delete("ID=?",selectionArgs)
                LoadQuery("%")
            })
            myview.imageView.setOnClickListener( View.OnClickListener{
                GoToUpdate(mynote)
            })
            return  myview
        }

        override fun getItem(position: Int): Any {
            return notelists[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return notelists.size
        }

    }

    fun GoToUpdate(note:note){
        var intent=  Intent(this,AddNotes::class.java)
        intent.putExtra("ID",note.id)
        intent.putExtra("name",note.title)
        intent.putExtra("des",note.des)
        startActivity(intent)
    }
}