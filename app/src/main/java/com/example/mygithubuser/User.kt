package com.example.mygithubuser

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var name: String,
    var username: String,
    var avatar: Int,
    var location : String,
    var company : String) : Parcelable