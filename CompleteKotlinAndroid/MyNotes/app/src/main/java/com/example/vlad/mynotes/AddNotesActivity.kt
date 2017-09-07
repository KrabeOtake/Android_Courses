package com.example.vlad.mynotes

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*
import java.net.IDN

class AddNotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)
    }

    fun buAdd(view:View){
        val dbmanager = DBManager(this)

        var values = ContentValues()
        values.put("Title", etTitle.text.toString())
        values.put("Description", etDesc.text.toString())

        val ID = dbmanager.Insert(values)
        if(ID!! > 0){
            Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Cannot add note", Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
