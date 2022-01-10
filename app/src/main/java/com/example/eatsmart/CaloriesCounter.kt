package com.example.eatsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*

class CaloriesCounter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calories_counter)

        //declaring all variables
        val age : TextView = findViewById(R.id.ageTextView)
        val height : TextView = findViewById(R.id.heightTextView)
        val weight : TextView = findViewById(R.id.weightTextView)
        val calcBtn : Button = findViewById(R.id.calcButton)
        val result : TextView = findViewById(R.id.resultTextView)
        val genderSpinner : Spinner = findViewById(R.id.genderSpinner)

        var flag : String = " "
        var genders = arrayOf("Female", "Male")
        genderSpinner.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, genders)

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                flag = genders.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

//        calcBtn.setOnClickListener {
//            //different equation for men and women
//            if(flag == "Female"){
//                result.text = calCalories('F',
//                    age.text.toInt(), height.text.toDouble(), weight.text.toDouble()).toString()
//            }
//
//            else if(flag == "Male"){
//                result.text = calCalories('M',
//                    age.text.toInt(), height.text.toDouble(), weight.text.toDouble()).toString()            }
//        }
    }
}

public fun calCalories(gender : Char, age : Int, height : Double, weight : Double) {
    if(gender == 'F'){
        return ;
    }

    else if(gender == 'M'){
        return ;
    }
}