package com.example.eatsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.*

class AppServices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_services)

        val allergiesList = intent.getSerializableExtra( "key" )

        val findRestaurant = findViewById<ImageView>(R.id.imageView5)
        findRestaurant.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            intent.putExtra("key", allergiesList)
            startActivity(intent)
        }

        val submitRestaurant = findViewById<ImageView>(R.id.imageView4)
        submitRestaurant.setOnClickListener{
            //this part helps the user open gmail and send an email to the specified email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("eatsmartjo@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Suggesting More Restaurants")
            intent.putExtra(Intent.EXTRA_TEXT, "mail body")
            startActivity(Intent.createChooser(intent, ""))
        }

        val calcCalories = findViewById<ImageView>(R.id.imageView3)
        calcCalories.setOnClickListener{
            val intent = Intent(this, CaloriesCounter::class.java)
            startActivity(intent)
        }
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