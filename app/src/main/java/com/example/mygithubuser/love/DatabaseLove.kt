package com.example.mygithubuser.love

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataUserLove::class],version = 1)
abstract class DatabaseLove: RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE : DatabaseLove? = null

        fun Database(context: Context): DatabaseLove?{
            if (INSTANCE == null){
                synchronized(DatabaseLove::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseLove::class.java, "database_userlove").build()
                }
            }
            return INSTANCE
        }
    }
    abstract fun dataLove(): LoveDao
}