package com.example.vlad.mynotes

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast

/**
 * Created by Vlad on 07.09.2017.
 */
class DBManager{
    val dbName = "MyNotes"
    val dbTables = "Notes"
    val colID = "ID"
    val colTitle = "Title"
    val colDesc = "Description"
    val dbVersion = 1
    val sqlCtreateTable = "CREATE TABLE IF NOT EXISTS " + dbTables + " (" + colID + " INTEGER PRIMARY KEY, " + colTitle + " TEXT, " + colDesc + " TEXT);"
    var sqlDB: SQLiteDatabase? = null

    constructor(context:Context){
        val db = dataBaseHelperNotes(context)
        sqlDB = db.writableDatabase
    }

    inner class dataBaseHelperNotes:SQLiteOpenHelper{
        var context:Context? = null
        constructor(context: Context):super(context,dbName,null,dbVersion){
            this.context = context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db!!.execSQL(sqlCtreateTable)
            Toast.makeText(this.context,"database is created", Toast.LENGTH_SHORT).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS " + dbTables)
        }

    }

    fun Insert(values:ContentValues):Long{
        val ID = sqlDB!!.insert(dbTables, "", values)
        return ID
    }

    fun Query(projection:Array<String>, selection:String, selectionArgs:Array<String>, sorOrder:String): Cursor {

        val qb = SQLiteQueryBuilder()
        qb.tables = dbTables
        val cursor = qb.query(sqlDB, projection, selection, selectionArgs, null, null, sorOrder)
        return cursor
    }

    fun Delete(selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.delete(dbTables, selection,selectionArgs)
        return count
    }

    fun Update(values:ContentValues,selection:String,selectionargs:Array<String>):Int{

        val count=sqlDB!!.update(dbTables,values,selection,selectionargs)
        return count
    }
}