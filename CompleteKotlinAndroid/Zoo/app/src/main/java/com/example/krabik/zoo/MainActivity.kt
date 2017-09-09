package com.example.krabik.zoo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Load animals
        listOfAnimals.add(Animal("Baboon", "Baboon lives in Africa", R.drawable.baboon, false))
        listOfAnimals.add(Animal("Bulldog", "Bulldog lives everywhere", R.drawable.bulldog, false))
        listOfAnimals.add(Animal("Panda", "Panda lives in Asia", R.drawable.panda, false))
        listOfAnimals.add(Animal("White tiger", "White tiger lives in Africa", R.drawable.white_tiger, true))
        listOfAnimals.add(Animal("Swallow bird", "Swallow bird lives in Africa", R.drawable.swallow_bird, false))
        listOfAnimals.add(Animal("Zebra", "Zebra lives in Africa", R.drawable.zebra, false))

        adapter = AnimalAdapter(this, listOfAnimals)
        lvAnimalList.adapter = adapter
    }

    class AnimalAdapter:BaseAdapter{

        var listOfAnimals = ArrayList<Animal>()
        var context: Context? = null
        constructor(context:Context, listOfAnimals:ArrayList<Animal>):super(){
            this.listOfAnimals = listOfAnimals
            this.context = context
        }
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            if(listOfAnimals[p0].isKiller == true){
                val animal = listOfAnimals[p0]
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_killer_ticket, null)
                myView.tvName.text = animal.name
                myView.tvDesc.text = animal.description
                myView.ivImage.setImageResource(animal.image!!)
                myView.ivImage.setOnClickListener{
                    var intent = Intent(context,AnimalInfoActivity::class.java)
                    intent.putExtra("Name", animal.name)
                    intent.putExtra("Description", animal.description)
                    intent.putExtra("Image", animal.image!!)
                    intent.putExtra("isKiller", animal.isKiller)
                    context!!.startActivity(intent)
                }
                return myView
            }else{
                val animal = listOfAnimals[p0]
                var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                var myView = inflator.inflate(R.layout.animal_ticket, null)
                myView.tvName.text = animal.name
                myView.tvDesc.text = animal.description
                myView.ivImage.setImageResource(animal.image!!)
                myView.ivImage.setOnClickListener{
                    var intent = Intent(context,AnimalInfoActivity::class.java)
                    intent.putExtra("Name", animal.name)
                    intent.putExtra("Description", animal.description)
                    intent.putExtra("Image", animal.image!!)
                    intent.putExtra("isKiller", animal.isKiller)
                    context!!.startActivity(intent)
                }
                return myView
            }

        }

        override fun getItem(p0: Int): Any {
            return listOfAnimals[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}
