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

        val URL = "content://com.example.EatSmart.UsersProvider"
        val users = Uri.parse(URL)

        val cols = listOf<String>(UsersProvider.email,
            UsersProvider.password).toTypedArray()

//        var rs = contentResolver.query(users,
//                cols,
//            "${cols[0]} LIKE ?",
//                Array(1){"%${userEmail.text}%"},
//                null)

//        var rs = contentResolver.query(users,
//                null,
//            null,
//                null,
//                null)
//
//        if(rs != null){
//            val intent = Intent(this, AllergiesSelection::class.java)
//            startActivity(intent)
//        }

//        when (rs?.count) {
//            null -> {
//                Toast.makeText(applicationContext, "Not found!", Toast.LENGTH_SHORT).show()
//
//            }
//            0 -> {
//                Toast.makeText(applicationContext, "Not found!", Toast.LENGTH_SHORT).show()
//
//            }
//            else -> {
//                val intent = Intent(this, AllergiesSelection::class.java)
//                startActivity(intent)            }
//        }
//
        val intent = Intent(this, AllergiesSelection::class.java)
        startActivity(intent)
    }
}