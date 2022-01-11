package com.example.eatsmart

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val signInBtn : Button = findViewById(R.id.sign_in2)
        signInBtn.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }

    fun onClickAddUser() {
//        //add all attributes/columns to values (ContentValues variable) using put
//        val values = ContentValues()
//
//        //adding email
//        values.put(
//            StudentsProvider.EMAIL,
//            (findViewById<View>(R.id.editTextTextEmailAddress) as EditText).text.toString()
//        )
//
//        //adding full name
//        values.put(
//            StudentsProvider.NAME,
//            (findViewById<View>(R.id.editTextTextPersonName) as EditText).text.toString()
//        )
//
//        //adding email
//        values.put(
//            StudentsProvider.PASSWORD,
//            (findViewById<View>(R.id.editTextTextPassword) as EditText).text.toString()
//        )
//
//        //call insert function from UsersProvider
//        val uri = contentResolver.insert(StudentsProvider.CONTENT_URI, values)
//
//        //add a toast
//        Toast.makeText(baseContext, uri.toString() + " registered!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, SignIn::class.java)
        startActivity(intent)
    }
}