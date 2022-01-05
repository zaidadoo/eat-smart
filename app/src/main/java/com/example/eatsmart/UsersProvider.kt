package com.example.eatsmart
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.*
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.text.TextUtils
import java.lang.IllegalArgumentException
import java.util.HashMap

class UsersProvider : ContentProvider() {
    companion object{
        val PROVIDER_NAME = "com.example.EatSmart.UsersProvider"
        val URL = "content://" + PROVIDER_NAME + "/users"
        val CONTENT_URI = Uri.parse(URL)
        val userID = "userID"
        val email = "email"
        val fullName = "fullName"
        val password = "password"

        private val USERS_PROJECTION_MAP: HashMap<String, String>? = null
        val USERS = 1
        val USER_ID = 2
        val uriMatcher: UriMatcher? = null
        val DATABASE_NAME = "ESDatabase"
        val USERS_TABLE_NAME = "users"

        val DATABASE_VERSION = 1
        val CREATE_DB_TABLE =
            " CREATE TABLE " + USERS_TABLE_NAME +
                    " (userID INTEGER PRIMARY KEY AUTOINCREMENT" + "email TEXT, " + " fullname TEXT NOT NULL, " +
                    " password TEXT NOT NULL);"
    }

    //creating the database
    private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
    init{
        sUriMatcher.addURI(PROVIDER_NAME, "users", USERS);
        sUriMatcher.addURI(PROVIDER_NAME, "users/#", USER_ID);
    }

    private var db: SQLiteDatabase? = null
    private class DatabaseHelper internal constructor(context: Context?) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(CREATE_DB_TABLE)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS " + USERS_TABLE_NAME)
            onCreate(db)
        }
        }

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val rowID = db!!.insert(USERS_TABLE_NAME, "", values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }

        throw SQLException("Failed to add a record into $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }
}