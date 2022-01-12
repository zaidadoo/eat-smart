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
import android.provider.BaseColumns._ID
import android.text.TextUtils
import com.example.eatsmart.UsersProvider.Companion.CREATE_DB_TABLE
import com.example.eatsmart.UsersProvider.Companion.USERS_TABLE_NAME
import java.lang.IllegalArgumentException

class UsersProvider : ContentProvider() {
    companion object {
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
        val DATABASE_NAME = "eatSmart"
        val USERS_TABLE_NAME = "users"
        val DATABASE_VERSION = 1

        val CREATE_DB_TABLE = "CREATE TABLE users (userID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT NOT NULL, email TEXT NOT NULL, password TEXT NOT NULL)"
    }

    private var sUriMatcher = UriMatcher(UriMatcher.NO_MATCH);
    init
    {
        sUriMatcher.addURI(PROVIDER_NAME, "users", USERS);
        sUriMatcher.addURI(PROVIDER_NAME, "users/#", USER_ID);
    }

    private var db: SQLiteDatabase? = null
    /**
     * Helper class that actually creates and manages * the provider's underlying data repository.
     */
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
        /**
         * Create a write able database which will trigger its
         * creation if it doesn't already exist.  */

        db = dbHelper.writableDatabase
        return if (db == null) false else true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {

        var sortOrder = sortOrder
        val qb = SQLiteQueryBuilder()
        qb.tables = USERS_TABLE_NAME
        when (uriMatcher!!.match(uri)) {
            USER_ID -> qb.appendWhere(userID + "=" + uri.pathSegments[1])
            else -> {
                null
            }
        }

        if (sortOrder == null || sortOrder === "") {
            sortOrder = fullName
        }

        val c = qb.query(db, projection, selection, selectionArgs, null, null, sortOrder)
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

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        //values are sent in SignUp activity (ContentValues variable called "values")
        val rowID = db!!.insert(USERS_TABLE_NAME, "", values)
        if (rowID > 0) {
            val _uri = ContentUris.withAppendedId(CONTENT_URI, rowID)
            context!!.contentResolver.notifyChange(_uri, null)
            return _uri
        }
        throw SQLException("Failed to add a record into $uri")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        var count = 0;
        when(uriMatcher!!.match(uri)){
            USERS -> count = db!!.delete(
                USERS_TABLE_NAME, selection, selectionArgs
            )

            USER_ID -> {
                val id = uri.pathSegments[1]
                count = db!!.delete(
                    USERS_TABLE_NAME,
                    _ID + "=" + id + if(!TextUtils.isEmpty(selection)) " AND ($selection)" else "", selectionArgs
                )
            }
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }

        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        var count = 0
        when (uriMatcher!!.match(uri)) {
            USERS -> count = db!!.update(
                USERS_TABLE_NAME, values, selection,
                selectionArgs
            )
            USER_ID -> count = db!!.update(
                USERS_TABLE_NAME,
                values,
                _ID + " = " + uri.pathSegments[1] + (if (!TextUtils.isEmpty(selection)) " AND ($selection)" else ""),
                selectionArgs
            )
            else -> throw IllegalArgumentException("Unknown URI $uri")
        }
        context!!.contentResolver.notifyChange(uri, null)
        return count
    }

}