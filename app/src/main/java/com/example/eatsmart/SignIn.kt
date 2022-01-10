package com.example.eatsmart

import android.annotation.SuppressLint
import android.content.ContentResolver
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*
import android.database.Cursor

class SignIn : AppCompatActivity() {
    //var helper = DatabaseHelper(applicationContext)
    //var db = helper.readableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signInBtn : Button = findViewById(R.id.sign_up2)
        signInBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

}