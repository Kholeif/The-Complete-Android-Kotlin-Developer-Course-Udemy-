package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*


class Login : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    //database instance
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()
    }

    fun Login(view: View) {
        val email = editTextTextEmailAddress.text.toString()
        val password = editTextTextPassword.text.toString()
        loginToFirebase(email, password)
    }

    fun loginToFirebase(email:String , password:String){
        mAuth!!.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){task ->
            if(task.isSuccessful){
                Toast.makeText(applicationContext,"done", Toast.LENGTH_LONG).show()
                // save in database
                var current_user : FirebaseUser? = mAuth!!.currentUser
                myRef.child("users").child(current_user!!.email.toString().split("@")[0]).child("request").setValue(current_user.uid)
                LoadMain()
            }else{
                Toast.makeText(applicationContext,"failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LoadMain()
    }

    fun LoadMain(){
        var current_user : FirebaseUser? = mAuth!!.currentUser
        if (current_user!=null)
        {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", current_user.email.toString())
            intent.putExtra("uid", current_user.uid.toString())
            startActivity(intent)
        }
    }
}