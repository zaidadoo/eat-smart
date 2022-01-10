package com.example.eatsmart

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.UriMatcher
import android.os.Build.USER
import com.example.eatsmart.UsersProvider.Companion.USERS
import com.example.eatsmart.UsersProvider.Companion.USER_ID


private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)

fun init() {
    sUriMatcher.addURI(UsersProvider.toString(), "users", USERS)
    sUriMatcher.addURI(UsersProvider.toString(), "users/#", USER_ID)
}

private var db: SQLiteDatabase? = null

class DatabaseHelper internal constructor(context: Context?) :
    SQLiteOpenHelper(context, "users", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE users (userID INT PRIMARY KEY AUTOINCREMENT, fullName TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL)")
        //insert records
        db.execSQL("INSERT INTO users(fullName, email, password) VALUES ('Farah Ghanma', 'far20190403@std.psut.edu.jo', 'hello_world')")
        db.execSQL("INSERT INTO users(fullName, email, password) VALUES ('Zaid Issa', 'zaid@std.psut.edu.jo', 'hello_password')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + UsersProvider.USERS_TABLE_NAME)
        onCreate(db)
    }
}