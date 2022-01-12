package com.example.eatsmart

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*

class UserSettings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_settings)

    }

    fun onClickUpdateUser(){
        val values = ContentValues()
        //updating email
        values.put(
            UsersProvider.email,
            (findViewById<View>(R.id.userEmail) as EditText).text.toString()
        )

        //updating password
        values.put(
            UsersProvider.password,
            (findViewById<View>(R.id.userPassword) as EditText).text.toString()
        )

        //call insert function from UsersProvider
        val uri = contentResolver.update(UsersProvider.CONTENT_URI,
            values, "userID = ?",
            arrayOf((findViewById<View>(R.id.userEmail) as EditText).text.toString()))

        //add a toast
        Toast.makeText(baseContext, "Record Updated!", Toast.LENGTH_LONG).show()

        //after account has been updated, it takes the user back to the Main Activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun onClickDeleteUser(){

        //after account has been deleted, it takes the user back to the Main Activity
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.appServices -> {
                val intent = Intent(this, AppServices::class.java)
                startActivity(intent)
            }

            R.id.userSettings -> {
                val intent = Intent(this, UserSettings::class.java)
                startActivity(intent)
            }

            R.id.editAllergiesDiseases -> {
                val intent = Intent(this, AllergiesSelection::class.java)
                startActivity(intent)
            }

            R.id.signOut -> {
                val intent = Intent(this, SignIn::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}