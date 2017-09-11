package com.example.krabik.tictactoe2

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAuth:FirebaseAuth? = null
    private var database = FirebaseDatabase.getInstance()
    private var myRef = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
    }

    fun onLogin(view:View){
        LoginToFirebase(tvEmail.text.toString(), tvPassword.text.toString())
    }

    fun LoginToFirebase(email:String, password:String){

        mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        var currentUser = mAuth!!.currentUser
                        if(currentUser != null){
                            myRef.child("Users").child(SplitString(currentUser.email.toString())).child("Request").setValue(currentUser.uid)
                        }
                        LoadMain()
                    }else{
                        Toast.makeText(this, "Fail", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    override fun onStart() {
        super.onStart()
        LoadMain()
    }

    fun LoadMain(){
        var currentUser = mAuth!!.currentUser

        if(currentUser != null) {
            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("UID", currentUser.uid)

            startActivity(intent)
            finish()
        }
    }

    fun  SplitString(str:String):String{
        var split=str.split("@")
        return split[0]
    }
}
