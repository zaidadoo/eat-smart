package com.example.eatsmart

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*
import android.widget.EditText
import android.widget.Toast
import android.database.Cursor

import android.database.sqlite.SQLiteDatabase

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signUpBtn : Button = findViewById(R.id.sign_up2)
        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    fun onClickCheckUserCredentials(view: View?) {
        val userEmail : EditText = findViewById(R.id.userEmail)
        val userPassword : EditText = findViewById(R.id.userPassword)

//        val URL = "content://com.example.EatSmart.UsersProvider"
//        val users = Uri.parse(URL)
//
////        var c = contentResolver.query(users, null, null, null,null)
////        var flag = 0
//
//        var rs = contentResolver.query(users,
//            null,
//            "email = " + userEmail + " AND password = " + userPassword,
//            null, null)
//
//        if (rs?.moveToNext() == true){
//            val intent = Intent(this, AllergiesSelection::class.java)
//            startActivity(intent)
//
//
//        }

//        //no records of those credentials were found
//        if(flag == 0){
//            Toast.makeText(applicationContext, "User not found!", Toast.LENGTH_SHORT).show()
//        }
        val intent = Intent(this, AllergiesSelection::class.java)
        startActivity(intent)
    }
}