package com.example.mynotes2

import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_add_note_fragment.*

class AddNote_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_note_fragment, container, false)
    }

    var id:Int?=0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Addbutton.setOnClickListener { addnote() }
        setHasOptionsMenu(true)

        id = arguments?.getInt("ID")
        if (id!=0){
            val title = arguments?.getString("Title")
            val description = arguments?.getString("Description")
            editText.setText(title)
            editText2.setText(description)
        }

    }

    fun addnote() {
    val db=DbManager(this.requireActivity())
        val values = ContentValues()
        values.put("Title",editText.text.toString())
        values.put("Description",editText2.text.toString())

        if(id!=0){ // edit
            val id = db.Update(values,"ID=?", arrayOf(id.toString()))
            if(id>0) { Toast.makeText(this.activity,"Note is edited",Toast.LENGTH_LONG).show() }
            else{ Toast.makeText(this.activity,"Note failed to be edited",Toast.LENGTH_LONG).show() }
        }
        else{ // Add new
            val id = db.Insert(values)
            if(id>0) { Toast.makeText(this.activity,"Note is added",Toast.LENGTH_LONG).show() }
            else{ Toast.makeText(this.activity,"Note failed to be added",Toast.LENGTH_LONG).show() }
        }
    }



    // Menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu2,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.back -> view?.findNavController()?.navigate(R.id.myNotesList_fragent)
        }
        return super.onOptionsItemSelected(item)
    }

}
