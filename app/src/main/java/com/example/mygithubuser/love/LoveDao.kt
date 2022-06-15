package com.example.mygithubuser.love

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoveDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun AddLoveUser(love: DataUserLove)

    @Query("SELECT * FROM tableLove")
    fun GetDataLove(): LiveData<List<DataUserLove>>

    @Query("SELECT count(*) FROM tableLove WHERE tableLove.id_userlove = :id")
     fun checkUser(id: Int): Int

    @Query("DELETE FROM tableLove WHERE tableLove.id_userlove = :id")
     fun removeFromFavorite(id: Int): Int

}