package com.example.eatsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.*

class AppServices : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.app_services)

        val findRestaurant = findViewById<ImageView>(R.id.imageView5)
        findRestaurant.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        val submitRestaurant = findViewById<ImageView>(R.id.imageView4)
        submitRestaurant.setOnClickListener{
            //this part helps the user open gmail and send an email to the specified email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("eatsmartjo@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject")
            intent.putExtra(Intent.EXTRA_TEXT, "mail body")
            startActivity(Intent.createChooser(intent, ""))
        }

        val calcCalories = findViewById<ImageView>(R.id.imageView3)
        calcCalories.setOnClickListener{
            val intent = Intent(this, CaloriesCounter::class.java)
            startActivity(intent)
        }
    }
}