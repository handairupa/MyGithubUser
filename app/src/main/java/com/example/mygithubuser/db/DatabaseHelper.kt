package com.example.mygithubuser.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.mygithubuser.db.DatabaseContract.LoveColumns.Companion.TABLE_NAME

internal class DatabaseHelper (context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "dbloveapp"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_LOVE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.LoveColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ${DatabaseContract.LoveColumns.NAME} TEXT NOT NULL, " +
                "${DatabaseContract.LoveColumns.FOLLOWERS}TEXT NOT NULL, " +
                "${DatabaseContract.LoveColumns.FOLLOWING} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_LOVE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}