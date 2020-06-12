package com.example.mynotes2

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_my_notes_list_fragent.*
import kotlinx.android.synthetic.main.ticket.view.*

class MyNotesList_fragent : Fragment() {

    var noteslist = ArrayList<note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_notes_list_fragent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        // noteslist.add(note(1,"Messi","Lionel Andrés Messi Cuccittini[note 1] (Spanish pronunciation: [ljoˈnel anˈdɾez ˈmesi] (About this soundlisten);[A] born 24 June 1987) is an Argentine professional footballer who plays as a forward and captains both Spanish club Barcelona and the Argentina national team. Often considered the best player in the world and widely regarded as one of the greatest players of all time, Messi has won a record six Ballon d'Or awards,[note 2] and a record six European Golden Shoes. He has spent his entire professional career with Barcelona, where he has won a club-record 34 trophies, including ten La Liga titles, four UEFA Champions League titles and six Copas del Rey. A prolific goalscorer and a creative playmaker, Messi holds the records for most goals in La Liga (438), a La Liga and European league season (50), most hat-tricks in La Liga (36) and the UEFA Champions League (8), and most assists in La Liga (181) and the Copa América (12). He has scored over 700 senior career goals for club and country."))
        // noteslist.add(note(2,"Early life","Born and raised in central Argentina, Messi relocated to Spain to join Barcelona at age 13, for whom he made his competitive debut aged 17 in October 2004. He established himself as an integral player for the club within the next three years, and in his first uninterrupted season in 2008–09 he helped Barcelona achieve the first treble in Spanish football; that year, aged 22, Messi won his first Ballon d'Or. Three successful seasons followed, with Messi winning four consecutive Ballons d'Or, making him the first player to win the award four times and in a row.[9] During the 2011–12 season, he set the La Liga and European records for most goals scored in a single season, while establishing himself as Barcelona's all-time top scorer. The following two seasons, Messi finished second for the Ballon d'Or behind Cristiano Ronaldo (his perceived career rival), before regaining his best form during the 2014–15 campaign, becoming the all-time top scorer in La Liga and leading Barcelona to a historic second treble, after which he was awarded a fifth Ballon d'Or in 2015. Messi assumed the captaincy of Barcelona in 2018, and in 2019 he secured a record sixth Ballon d'Or."))
        // noteslist.add(note(3,"Argentine","An Argentine international, Messi is his country's all-time leading goalscorer. At youth level, he won the 2005 FIFA World Youth Championship, finishing the tournament with both the Golden Ball and Golden Shoe, and an Olympic gold medal at the 2008 Summer Olympics. His style of play as a diminutive, left-footed dribbler drew comparisons with his compatriot Diego Maradona, who described Messi as his successor. After his senior debut in August 2005, Messi became the youngest Argentine to play and score in a FIFA World Cup during the 2006 edition, and reached the final of the 2007 Copa América, where he was named young player of the tournament. As the squad's captain from August 2011, he led Argentina to three consecutive finals: the 2014 FIFA World Cup, for which he won the Golden Ball, and the 2015 and 2016 Copas América. After announcing his international retirement in 2016, he reversed his decision and led his country to qualification for the 2018 FIFA World Cup, and a third-place finish at the 2019 Copa América."))

        query("%")
    }

    // Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.Add_note -> view?.findNavController()?.navigate(R.id.action_myNotesList_fragent_to_addNote_fragment)
        }
        return super.onOptionsItemSelected(item)
    }

    fun query(search:String){
        val dbManager = DbManager(this.requireActivity())
        val cursor = dbManager.Query(arrayOf("ID","Title","Description"),"Title like ?", arrayOf(search),"Title")
        if(cursor.moveToFirst())
        {
            noteslist.clear()
            do {
                val id = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Description = cursor.getString(cursor.getColumnIndex("Description"))
                noteslist.add(note(id,Title,Description))
            }while (cursor.moveToNext())
        }
        val myAdapter = NotesAdapter(this.requireActivity(),noteslist)
        listview.adapter = myAdapter
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
            val mynote = notelists[position]
            val myview=layoutInflater.inflate(R.layout.ticket,null)
            myview.textView.text=mynote.title!!
            myview.textView2.text=mynote.des!!

            // delete
            myview.imageView2.setOnClickListener( View.OnClickListener {
                val dbManager=DbManager(this.context!!)
                val selectionArgs= arrayOf(mynote.id.toString())
                dbManager.Delete("ID=?",selectionArgs)
                query("%")
            })

            // update
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

    private fun GoToUpdate(mynote: note) {
        val bundle = Bundle()
        bundle.putInt("ID",mynote.id!!)
        bundle.putString("Title",mynote.title!!)
        bundle.putString("Description",mynote.des!!)
        view?.findNavController()?.navigate(R.id.action_myNotesList_fragent_to_addNote_fragment,bundle)
    }

}
