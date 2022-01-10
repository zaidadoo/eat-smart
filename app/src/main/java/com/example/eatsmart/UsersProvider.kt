package com.example.eatsmart
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.content.*
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import java.lang.IllegalArgumentException

class UsersProvider : ContentProvider() {
    companion object{
        val PROVIDER_NAME = "com.example.EatSmart.UsersProvider"
        val URL = "content://" + PROVIDER_NAME + "/users"
        val CONTENT_URI = Uri.parse(URL)

        val userID = "userID"
        val email = "email"
        val fullName = "fullName"
        val password = "password"

        val USERS = 1
        val USER_ID = 2
        val uriMatcher: UriMatcher? = null
        val USERS_TABLE_NAME = "users"
    }

    lateinit var db : SQLiteDatabase

    override fun onCreate(): Boolean {
        val context = context
        val dbHelper = DatabaseHelper(context)
        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(
        uri: Uri,
        cols: Array<out String>?,
        condition: String?,
        condition_val: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = USERS_TABLE_NAME
        when (uriMatcher!!.match(uri)) {
            USER_ID -> qb.appendWhere(userID + "=" + uri.pathSegments[1])
            else -> { null
            }
        }

        if (sortOrder == null || sortOrder === "") {
            sortOrder = fullName
        }

        val c = qb.query(db, cols, condition, condition_val, null, null, sortOrder)
        c.setNotificationUri(context!!.contentResolver, uri)
        return c
    }

    override fun getType(uri: Uri): String? {
        when (uriMatcher!!.match(uri)) {
            USERS -> return "vnd.android.cursor.dir/vnd.example.users"
            USER_ID -> return "vnd.android.cursor.item/vnd.example.users"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri {
        //values are sent in SignUp activity (ContentValues variable called "values")
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