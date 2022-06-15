package com.example.mygithubuser.find

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PencarianAkun(
    var name: String?,
    var avatar: String?,
) : Parcelable
