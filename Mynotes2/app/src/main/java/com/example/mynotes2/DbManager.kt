package com.example.mynotes2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.media.projection.MediaProjection
import android.widget.Toast
import androidx.fragment.app.FragmentActivity


class  DbManager{

    val dbName="MyNotes"
    val dbTable="Notes"
    val colID="ID"
    val colTitle="Title"
    val colDes="Description"
    val dbVersion=1
    //CREATE TABLE IF NOT EXISTS MyNotes (ID INTEGER PRIMARY KEY,title TEXT, Description TEXT);"
    val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+ dbTable +" ("+ colID +" INTEGER PRIMARY KEY,"+
            colTitle + " TEXT, "+ colDes +" TEXT);"
    var sqlDB:SQLiteDatabase?=null

    constructor(context:Context){
        val db=DatabaseHelperNotes(context)
        sqlDB=db.writableDatabase
    }



    //Create database
    inner class  DatabaseHelperNotes:SQLiteOpenHelper
    {
        var context:Context?=null
        constructor(context:Context):super(context,dbName,null,dbVersion){ this.context=context }

        override fun onCreate(db: SQLiteDatabase?)
        {
            db!!.execSQL(sqlCreateTable)
            Toast.makeText(this.context," database is created", Toast.LENGTH_LONG).show()
        }


        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
        {
            db!!.execSQL("Drop table IF EXISTS " + dbTable)
        }
    }




    // Insert
    fun Insert(values:ContentValues):Long {
        val ID= sqlDB!!.insert(dbTable,"",values)
        return ID
    }

    // Query                   projection : ela3meda elly 3wezha ,,,,, selection : bteb2a kalam zy "Title like ?" ,,,,, selectionArgs : hadawar 3n eh
    fun Query(projection:Array<String> , selection:String , selectionArgs:Array<String> , sortOrder:String):Cursor{
        val db = SQLiteQueryBuilder()
        db.tables = dbTable
        val cursor = db.query(sqlDB,projection,selection,selectionArgs,null,null,sortOrder)
        return cursor
    }

    // Delete
    fun Delete(selection:String , selectionArgs:Array<String>):Int{
        val count = sqlDB!!.delete(dbTable,selection,selectionArgs)
        return count
    }

    // Update
    fun Update(values:ContentValues , selection:String , selectionArgs:Array<String>):Int{
        val count = sqlDB!!.update(dbTable,values,selection,selectionArgs)
        return count
    }
}
