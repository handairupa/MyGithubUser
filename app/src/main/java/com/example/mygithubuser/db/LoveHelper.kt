package com.example.mygithubuser.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.example.mygithubuser.db.DatabaseContract.LoveColumns.Companion.TABLE_NAME
import com.example.mygithubuser.db.DatabaseContract.LoveColumns.Companion._ID
import kotlin.jvm.Throws

class LoveHelper(context: Context) {

    private var databaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object{
        private const val DATABASE_TABLE = TABLE_NAME

        private var INSTANCE: LoveHelper? = null
        fun getInstance(context: Context):LoveHelper =
            INSTANCE ?: synchronized(this){
                INSTANCE ?: LoveHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }
    fun close(){
        databaseHelper.close()

        if(database.isOpen)
            database.close()
    }
    
    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID ASC")
    }

    fun querById(id : String): Cursor{
        return database.query(
            DATABASE_TABLE,
            null,
            "$_ID = ?",
            arrayOf(id),
            null,
            null,
            null,
            null)
    }
    fun insert(values: ContentValues?): Long{
        return database.insert(DATABASE_TABLE, null, values)
    }
    fun update(id: String, values: ContentValues?):Int{
        return database.update(DATABASE_TABLE, values, "$_ID =?", arrayOf(id))
    }
    fun delete(id: String):Int{
        return database.delete(DATABASE_TABLE, "$_ID = '$id'", null)
    }
}