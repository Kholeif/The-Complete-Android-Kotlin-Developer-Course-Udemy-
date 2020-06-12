package com.example.mynotes2

class note {
    var id: Int? = null
    var title: String? = null
    var des: String? = null

    constructor(id: Int, title: String, des: String) {
        this.id = id
        this.title = title
        this.des = des
    }
}