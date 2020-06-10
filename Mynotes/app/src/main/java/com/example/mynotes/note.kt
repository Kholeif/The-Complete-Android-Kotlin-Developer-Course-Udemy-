package com.example.mynotes

import android.icu.text.CaseMap

class note{
    var id:Int?=null
    var title:String?=null
    var des:String?=null
    constructor(id:Int,title:String,des:String){
        this.id= id
        this.title=title
        this.des=des
    }
}