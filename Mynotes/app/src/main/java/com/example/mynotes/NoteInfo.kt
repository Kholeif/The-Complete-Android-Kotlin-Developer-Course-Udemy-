package com.example.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_info.*

class NoteInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_info)

        var bundle:Bundle = intent.extras!!
        val title = bundle.getString("title")
        val des = bundle.getString("des")

        textView3.setText(title)
        textView4.setText(des)
    }
}
