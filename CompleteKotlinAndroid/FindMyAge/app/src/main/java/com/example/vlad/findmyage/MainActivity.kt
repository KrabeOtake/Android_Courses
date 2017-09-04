package com.example.vlad.findmyage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnFindAge.setOnClickListener {
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            val yearOfBirth = etAge.text.toString().toInt()
            val age = currentYear - yearOfBirth
            tvAge.text = age.toString()
        }
    }
}
