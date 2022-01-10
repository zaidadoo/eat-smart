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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signUpBtn : Button = findViewById(R.id.sign_up2)
        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        val signInBtn : Button = findViewById(R.id.sign_in2)
        signInBtn.setOnClickListener{
            //double check the record is there
            //

            //take user to app services if it is
            val intent = Intent(this, AppServices::class.java)
            startActivity(intent)
        }
    }
}