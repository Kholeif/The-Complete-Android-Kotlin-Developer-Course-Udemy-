package com.example.zooapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.animal_ticket.view.*

class Animal {

    var name: String? = null
    var des: String? = null
    var image: Int? = null
    var iskiller:Boolean = false

    constructor(name: String, des: String, image: Int , iskiller:Boolean) {
        this.name = name
        this.des = des
        this.image = image
        this.iskiller=iskiller
    }
}
