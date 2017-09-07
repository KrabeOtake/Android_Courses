package com.example.krabik.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buNumberEvent(view:View){
        if(isNewOp == true){
            etShownData.setText("")
        }
        isNewOp = false
        val buSelected = view as Button
        var buClickValue:String = etShownData.text.toString()
        when(buSelected.id){
            bu0.id -> buClickValue += "0"
            bu1.id -> buClickValue += "1"
            bu2.id -> buClickValue += "2"
            bu3.id -> buClickValue += "3"
            bu4.id -> buClickValue += "4"
            bu5.id -> buClickValue += "5"
            bu6.id -> buClickValue += "6"
            bu7.id -> buClickValue += "7"
            bu8.id -> buClickValue += "8"
            bu9.id -> buClickValue += "9"
            buDot.id -> buClickValue += "."
            buPlusMinus.id -> buClickValue = "-" + buClickValue
        }
        etShownData.setText(buClickValue)

    }

    var op = ""
    var oldNumber = ""
    var isNewOp = true

    fun buOpEvent(view:View){

        val buSelected = view as Button
        var buClickValue:String = etShownData.text.toString()
        when(buSelected.id){
            buPlus.id -> {
                op = "+"
            }
            buMinus.id -> {
                op = "-"
            }
            buMultiply.id -> {
                op = "*"
            }
            buDivide.id -> {
                op = "/"
            }
        }
        oldNumber = etShownData.text.toString()
        isNewOp = true

    }

    fun buEqualEvent(view:View){
        var newNumber = etShownData.text.toString()
        var finalNumber:Double? = null
        when(op){
            "+" -> {
                finalNumber = oldNumber.toDouble() + newNumber.toDouble()
            }
            "-" -> {
                finalNumber = oldNumber.toDouble() - newNumber.toDouble()
            }
            "*" -> {
                finalNumber = oldNumber.toDouble() * newNumber.toDouble()
            }
            "/" -> {
                finalNumber = oldNumber.toDouble() / newNumber.toDouble()
            }
        }
        etShownData.setText(finalNumber.toString())
        isNewOp = true
    }

    fun buPercent(view:View){
        var number:Double = etShownData.text.toString().toDouble()/100
        etShownData.setText(number.toString())
    }

    fun buClean(view:View){
        etShownData.setText("")
        isNewOp = true
    }
}
