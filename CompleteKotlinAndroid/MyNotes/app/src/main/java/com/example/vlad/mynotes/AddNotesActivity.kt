package com.example.vlad.mynotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun buAdd(view:View){
        finish()
    }
}
