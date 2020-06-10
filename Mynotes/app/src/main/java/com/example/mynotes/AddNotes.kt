package com.example.mynotes

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*
import kotlinx.android.synthetic.main.ticket.*
import java.lang.Exception

class AddNotes : AppCompatActivity() {
    val dbName="MyNotes"
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)


        try {
            var bundle:Bundle = intent.extras!!
            id = bundle.getInt("ID", 0)
            if (id != 0) {
                editText.setText(bundle.getString("name").toString())
                editText2.setText(bundle.getString("des").toString())
            }
        }catch (ex:Exception){}
    }

    fun addNote(view: View) {
        var db = DbManager(this)
        var values = ContentValues()
        values.put("Title",editText.text.toString())
        values.put("Description",editText2.text.toString())


        if (id==0)
        {
            val id = db.Insert(values)
            if(id>0)
            {
                Toast.makeText(this,"note is added",Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
            Toast.makeText(this,"can not add note",Toast.LENGTH_LONG).show()
            }
        }
        else
        {
            var selectionArs = arrayOf(id.toString())
            val id = db.Update(values,"ID=?",selectionArs)
            if(id>0)
            {
                Toast.makeText(this,"note is added",Toast.LENGTH_LONG).show()
                finish()
            }
            else
            {
                Toast.makeText(this,"can not add note",Toast.LENGTH_LONG).show()
            }
        }
    }
}
