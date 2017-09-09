package com.example.krabik.zoo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val b:Bundle = intent.extras
        val name = b.getString("Name")
        val description = b.getString("Description")
        val image = b.getInt("Image")

        tvTitle.text = name
        tvDescription.text = description
        ivAnimalImage.setImageResource(image)
    }
}
