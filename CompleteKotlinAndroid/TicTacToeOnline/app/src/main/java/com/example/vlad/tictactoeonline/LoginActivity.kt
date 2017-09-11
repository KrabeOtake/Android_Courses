package com.example.vlad.tictactoeonline

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    private var databas = FirebaseDatabase.getInstance()
    private var myRef = databas.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
    }

    fun LoginEvent(view:View){

        LoginFirebase(etLogin.text.toString(), etPassword.text.toString())
    }

    fun LoginFirebase(email:String, password:String){

        mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                    }
        }
    }

    override fun onStart() {
        super.onStart()
        LoadMain()
    }


    fun  LoadMain(){
        var currentUser =mAuth!!.currentUser

        if(currentUser!=null) {

            myRef.child("Users").child(currentUser.uid).child(currentUser.email)

            var intent = Intent(this, MainActivity::class.java)
            intent.putExtra("email", currentUser.email)
            intent.putExtra("uid", currentUser.uid)

            startActivity(intent)
            finish()
        }
    }
}
