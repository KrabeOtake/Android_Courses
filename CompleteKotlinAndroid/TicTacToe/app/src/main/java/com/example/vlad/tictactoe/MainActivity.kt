package com.example.vlad.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.vlad.tictactoe.R.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
    }

    protected fun buClick(view:View){
        val buSelected = view as Button
        var cellID = 0
        when(buSelected.id){
            id.button -> cellID = 1
            id.button2 -> cellID = 2
            id.button3 -> cellID = 3
            id.button4 -> cellID = 4
            id.button5 -> cellID = 5
            id.button6 -> cellID = 6
            id.button7 -> cellID = 7
            id.button8 -> cellID = 8
            id.button9 -> cellID = 9
        }

        Toast.makeText(this, "ID = $cellID", Toast.LENGTH_SHORT).show()

        PlayGame(cellID, buSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var ActivePlayer = 1

    @SuppressLint("ResourceAsColor")
    protected fun PlayGame(cellID: Int, buSelected: Button){

        if(ActivePlayer == 1){
            buSelected.text = "X"
            buSelected.setBackgroundResource(color.darkgreen)
            player1.add(cellID)
            ActivePlayer = 2
            AutoPlay()
        }else{
            buSelected.text = "O"
            buSelected.setBackgroundResource(color.colorPrimary)
            player1.add(cellID)
            ActivePlayer = 1
        }

        buSelected.isEnabled = false

        CheckWinner()
    }

    fun CheckWinner(){
        var winner = -1

        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        //diag 1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner = 2
        }

        //diag 2
        if(player1.contains(3) && player1.contains(5) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(5) && player2.contains(7)){
            winner = 2
        }

        if(winner != -1){
            Toast.makeText(this, "Player $winner is a winner!", Toast.LENGTH_SHORT).show()
        }
    }

    fun AutoPlay(){

        var emptyCells = ArrayList<Int>()
        for(cell in 1..9){
            if(!(player1.contains(cell) || player2.contains(cell))){
                emptyCells.add(cell)
            }
        }

        val r = Random()
        val randomIndex = r.nextInt(emptyCells.size)
        val cellID = emptyCells[randomIndex]

        var buSelected: Button?
        when(cellID){
            1 -> buSelected = button
            2 -> buSelected = button2
            3 -> buSelected = button3
            4 -> buSelected = button4
            5 -> buSelected = button5
            6 -> buSelected = button6
            7 -> buSelected = button7
            8 -> buSelected = button8
            9 -> buSelected = button9
            else -> {
                buSelected = button
            }
        }

        PlayGame(cellID, buSelected)
    }
}
