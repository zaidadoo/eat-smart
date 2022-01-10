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
    var helper = DatabaseHelper(applicationContext)
    var db = helper.readableDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signInBtn : Button = findViewById(R.id.sign_up2)
        signInBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    @SuppressLint("Range")
    fun onClickCheckUserCredentials(view: View?) {
        //get user info
        val userEmail : EditText = findViewById(R.id.editTextTextEmailAddress)
        val userPassword : EditText = findViewById(R.id.editTextTextEmailAddress)

        val c: Cursor = db.rawQuery(
            "SELECT email, password FROM users WHERE email LIKE '" + userEmail.text.toString() + "'" +
                    "AND password LIKE '", null)

        if ((c.getString(c.getColumnIndex("email"))).equals(userEmail.text.toString())
            && (c.getString(c.getColumnIndex("password"))).equals(userPassword.text.toString())){
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)

            Toast.makeText(applicationContext, "Welcome!", Toast.LENGTH_SHORT).show()
        }

        else{
            Toast.makeText(applicationContext, "Invalid Credentials!", Toast.LENGTH_SHORT).show()
        }
    }
}