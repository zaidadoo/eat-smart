package com.example.eatsmart

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.*
import android.widget.RadioButton
import android.widget.Toast

class CaloriesCounter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calories_counter)

        //declaring all variables
        val age: TextView = findViewById(R.id.ageTextView)
        val height: TextView = findViewById(R.id.heightTextView)
        val weight: TextView = findViewById(R.id.weightTextView)
        val calcBtn: Button = findViewById(R.id.calcButton)
        var result: TextView = findViewById(R.id.resultTextView)
        val genderSpinner: Spinner = findViewById(R.id.genderSpinner)

        val radioGroup = findViewById<RadioGroup>(R.id.activityRadioGroup)
        val selectedOption = radioGroup.checkedRadioButtonId

        var flag = " "
        var genders = arrayOf("Female", "Male")
        genderSpinner.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1, genders
        )

        genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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
            //double check all fields are filled in
            if (age.text.isEmpty() || height.text.isEmpty() || weight.text.isEmpty() || radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(applicationContext, "All fields must be filled!", Toast.LENGTH_SHORT).show()
            } else {
                //store temporary result (before calculating it with activity) in a variable
                var temporaryResult = 0;
                val ageInt = age.text.toString()
                val heightDouble = height.text.toString()
                val weightDouble = weight.text.toString()

                //different equation for men and women
                if (flag == "Female") {
                    temporaryResult = calCalories('F',
                        ageInt.toInt(),
                        heightDouble.toDouble(),
                        weightDouble.toDouble())
                } else if (flag == "Male") {
                    temporaryResult = calCalories('M',
                        ageInt.toInt(),
                        heightDouble.toDouble(),
                        weightDouble.toDouble())
                }

                if (selectedOption.equals(findViewById<RadioButton>(R.id.firstGroupRB))) {
                    result.text = ((temporaryResult * 1.2).toInt().toString() + " Calories")
                } else if (selectedOption.equals(findViewById<RadioButton>(R.id.secondGroupRB))) {
                    result.text = ((temporaryResult * 1.375).toInt().toString() + " Calories")
                } else if (selectedOption.equals(findViewById<RadioButton>(R.id.thirdGroupRB))) {
                    result.text = ((temporaryResult * 1.55).toInt().toString() + " Calories")
                } else if (selectedOption.equals(findViewById<RadioButton>(R.id.fourthGroupRB))) {
                    result.text = ((temporaryResult * 1.725).toInt().toString() + " Calories")
                } else {
                    result.text = ((temporaryResult * 1.9).toInt().toString() + " Calories")
                }
            }
        }
    }
}

fun calCalories(gender : Char, age : Int, height : Double, weight : Double) : Int {
    //must give initial value
    var result = 0;
    if(gender == 'F'){
        //calculate BMR for women
        result = ((10 * weight) + (6.25 * height) - (5 * age) - 161 ).toInt()
    }

    else if(gender == 'M'){
        //calculate BMR for men
        result = ((10 * weight) + (6.25 * height) - (5 * age) + 5 ).toInt()
    }

    return result;
}