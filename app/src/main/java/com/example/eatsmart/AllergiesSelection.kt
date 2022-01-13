package com.example.eatsmart

import android.R.attr
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import java.lang.Class

import android.R.attr.key
import android.widget.CompoundButton
import java.util.ArrayList
import android.R.attr.key










class AllergiesSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.allergies_selection)

        val homeBtn : Button = findViewById(R.id.allergy_home)
        homeBtn.setOnClickListener {
            val intent = Intent(this, AppServices::class.java)
            startActivity(intent)
        }

        val suggestMore : Button = findViewById(R.id.suggest_more2)
        suggestMore.setOnClickListener {
            //this part helps the user open gmail and send an email to the specified email
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("eatsmartjo@gmail.com"))
            intent.putExtra(Intent.EXTRA_SUBJECT, "Suggesting More Allergies/Diseases")
            intent.putExtra(Intent.EXTRA_TEXT, "mail body")
            startActivity(Intent.createChooser(intent, ""))
        }

        //all allergies/diseases will be stored in this array and sent to the MapsActivity activity
        val allergiesList = ArrayList<String>()

        //declaring switch statement for each switch widget
        val hypertension : Switch = findViewById(R.id.hypertensionSwitch)
        val anemia : Switch = findViewById(R.id.anemiaSwitch)
        val celiac : Switch = findViewById(R.id.celiacSwitch)
        val diabetes : Switch = findViewById(R.id.diabetesSwitch)
        val kwashiorkor : Switch = findViewById(R.id.kwashiorkorSwitch)
        val rickets : Switch = findViewById(R.id.ricketsSwitch)
        val marasmus : Switch = findViewById(R.id.marasmusSwitch)

        hypertension.setOnCheckedChangeListener{
                _, isChecked -> allergiesList.add("hypertension")
        }

        anemia.setOnCheckedChangeListener{
                _, isChecked -> allergiesList.add("anemia")
        }

        celiac.setOnCheckedChangeListener{
                _, isChecked -> allergiesList.add("celiac")
        }

        diabetes.setOnCheckedChangeListener{
                _, isChecked -> allergiesList.add("diabetes")
        }

        kwashiorkor.setOnCheckedChangeListener{
                _, isChecked -> allergiesList.add("kwashiorkor")
        }

        rickets.setOnCheckedChangeListener{
                buttonView, isChecked -> allergiesList.add("rickets")
        }

        marasmus.setOnCheckedChangeListener{
                buttonView, isChecked -> allergiesList.add("marasmus")
        }

        //when home button is clicked, the AppServices activity opens and receives the array
        val home : Button = findViewById(R.id.allergy_home)
        home.setOnClickListener {
            val intent = Intent(this, AppServices::class.java)
            intent.putExtra("key", allergiesList)
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