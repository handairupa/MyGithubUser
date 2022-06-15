package com.example.mygithubuser.love

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableLove")
data class DataUserLove (

    @PrimaryKey
    @ColumnInfo(name = "id_userlove")
    val id_userlove: Int,

    @ColumnInfo(name = "username_userlove")
    val username_userlove: String,

    @ColumnInfo(name = "name_userlove")
    val name_userlove: String,

    @ColumnInfo(name = "avatar_userlove")
    val avatar_userlove: String,

    )