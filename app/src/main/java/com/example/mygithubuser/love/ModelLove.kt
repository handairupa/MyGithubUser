package com.example.mygithubuser.love

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelLove(application : Application): AndroidViewModel(application) {

    private var LoveDb : DatabaseLove? = DatabaseLove.Database(application)
    private var LoveDao : LoveDao? = LoveDb?.dataLove()

    fun getLove(): LiveData<List<DataUserLove>>?{
        return LoveDao?.GetDataLove()
    }

    fun AddLove(id_user : Int, username_user : String, name_user : String, avatar_user : String){
        CoroutineScope(Dispatchers.IO).launch {
            val loveuser = DataUserLove(
                id_user,
                username_user,
                name_user,
                avatar_user
            )
            LoveDao?.AddLoveUser(loveuser)
        }
    }

    fun DeleteLove(id_user: Int){
        CoroutineScope(Dispatchers.IO).launch {
            LoveDao?.removeFromFavorite(id_user)
        }
    }

    suspend fun checkLove(id_user: Int) =LoveDao?.checkUser(id_user)
}