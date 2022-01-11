package com.example.eatsmart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.*

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in)

        val signUpBtn : Button = findViewById(R.id.sign_up2)
        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    fun onClickCheckUserCredentials(view: View?) {
        // Retrieve student records
        val URL = "content://com.example.MyApplication.StudentsProvider"
        val students = Uri.parse(URL)
        //\  val c = contentResolver!!.query(students,null,null,null,"name")
        var c = contentResolver.query(students, null, null, null,null)
        //val //c = managedQuery(students, null, null, null, "name")
        if (c != null) {
            if (c?.moveToFirst()) {
                do {

                    Toast.makeText(this, c.getString(c.getColumnIndex(UsersProvider.userID)) +
                            ", " + c.getString(c.getColumnIndex(UsersProvider.fullName)) + ", "
                            + c.getString(c.getColumnIndex(UsersProvider.email)), Toast.LENGTH_SHORT).show()
                } while (c.moveToNext())
            }
        }
    }

}