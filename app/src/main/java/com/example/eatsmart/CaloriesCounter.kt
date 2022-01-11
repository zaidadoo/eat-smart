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

        calcBtn.setOnClickListener {
            val radioGroup = findViewById<RadioGroup>(R.id.minutesRadioGroup)
            val selectedMinutes : Int = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(selectedMinutes)

            //different equation for men and women
            if(flag == "Female"){
                result.text = calCalories('F',
                    age.text.toString().toInt(), height.text.toString().toDouble(), weight.text.toString().toDouble(), radioButton).toString()
            }

            else if(flag == "Male"){
                result.text = calCalories('M',
                    age.text.toString().toInt(), height.text.toString().toDouble(), weight.text.toString().toDouble(), radioButton).toString()
            }
        }
    }
}

public fun calCalories(gender : Char, age : Int, height : Double, weight : Double, radioButton : RadioButton) : String {
    var BMR : Double = 1.0
    var res : Double = 0.0

    when(radioButton.text) {
        "0-30" -> BMR = 1.2
        "30-60" -> BMR = 1.375
        "60-90" -> BMR = 1.55
        "90-120" -> BMR = 1.725
        "120+" -> BMR = 1.9
    }

    if(gender == 'F'){
        res = BMR * (10 * weight + 6.25 * height - 5 * age - 161)

    }

    else if(gender == 'M'){
        res = BMR * (10 * weight + 6.25 * height - 5 * age + 5)
    }

    return  res.toInt().toString() + " Calories"
}