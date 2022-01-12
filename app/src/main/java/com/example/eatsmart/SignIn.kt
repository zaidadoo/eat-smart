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
        // Retrieve student records
        val userEmail : EditText = findViewById(R.id.userEmail)
        val userPassword : EditText = findViewById(R.id.userPassword)
        val URL = "content://com.example.EatSmart.UsersProvider"
        val users = Uri.parse(URL)

        //val Query = "Select * from users where email = " + userEmail + " AND password = " + userPassword


//        var c = contentResolver.query(users, null, null, null,null)
//
//        if (c != null) {
//            if (c?.moveToFirst()) {
//                do {
//                    if(c.getString(c.getColumnIndex(UsersProvider.email)) == userEmail.text.toString()
//                                && c.getString(c.getColumnIndex(UsersProvider.password)) == userPassword.text.toString()){
//
//                        val intent = Intent(this, AllergiesSelection::class.java)
//                        startActivity(intent)
//                    }
//                } while (c.moveToNext())
//            }
//        }
    }
}