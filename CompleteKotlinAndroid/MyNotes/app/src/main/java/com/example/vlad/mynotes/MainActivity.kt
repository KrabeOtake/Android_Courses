package com.example.vlad.mynotes

import android.app.SearchManager
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket.view.*

class MainActivity : AppCompatActivity() {

    var listNotes = ArrayList<Node>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add dummy data
        listNotes.add(Node(1," meet professor","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))
        listNotes.add(Node(2," meet doctor","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))
        listNotes.add(Node(3," meet friend","Create any pattern of your own - tiles, texture, skin, wallpaper, comic effect, website background and more.  Change any artwork of pattern you found into different flavors and call them your own."))

        Toast.makeText(this,"onCreate",Toast.LENGTH_LONG).show()
        //Load from DB
        LoadQuery("%")
    }

    override  fun onResume() {
        super.onResume()
        LoadQuery("%")
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show()
    }

    fun LoadQuery(title:String){
        var dbManger = DBManager(this)
        val selectionArgs = arrayOf(title)
        val projection = arrayOf("ID", "Title", "Description")
        val cursor = dbManger.Query(projection, "Title like ?", selectionArgs, "Title")
        listNotes.clear()
        if(cursor.moveToFirst()){
            do{
                val ID = cursor.getInt(cursor.getColumnIndex("ID"))
                val Title = cursor.getString(cursor.getColumnIndex("Title"))
                val Desc = cursor.getString(cursor.getColumnIndex("Description"))

                listNotes.add(Node(ID, Title, Desc))

            }while (cursor.moveToNext())
        }
        val myNotesAdapter = MyNotesAdapter(this, listNotes)
        lvNotes.adapter = myNotesAdapter


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query:String): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_SHORT).show()
                LoadQuery("%" + query + "%")
                return false
            }

            override fun onQueryTextChange(newtext:String) : Boolean{
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.addNote ->{
                val intent = Intent(this, AddNotesActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    inner class MyNotesAdapter: BaseAdapter{

        var listNotesAdapter = ArrayList<Node>()
        var context:Context? = null
        constructor(context:Context, listNotesAdapter:ArrayList<Node>): super(){
            this.listNotesAdapter = listNotesAdapter
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val myView = layoutInflater.inflate(R.layout.ticket, null)
            val myNode = listNotesAdapter[position]
            myView.textView.text = myNode.nodeTitle
            myView.textView2.text = myNode.nodeDesc
            myView.ivDelete.setOnClickListener(View.OnClickListener {
                var dbManager = DBManager(this.context!!)
                val selectionArgs = arrayOf(myNode.nodeID.toString())
                dbManager.Delete("ID = ?", selectionArgs)
                LoadQuery("%")
            })
            myView.ivEdit.setOnClickListener(View.OnClickListener {
                var intent = Intent(this.context, AddNotesActivity::class.java)
                intent.putExtra("ID",myNode.nodeID)
                intent.putExtra("name",myNode.nodeTitle)
                intent.putExtra("des",myNode.nodeDesc)
                startActivity(intent)
            })
            return myView
        }

        override fun getItem(position: Int): Any {
            return listNotesAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listNotesAdapter.size
        }
    }
}
