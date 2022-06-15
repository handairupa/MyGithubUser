package com.example.mygithubuser.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class LoveColumns : BaseColumns {
        companion object {
            const val TABLE_NAME = "love"
            const val _ID = "username"
            const val NAME = "name"
            const val FOLLOWERS = "followers"
            const val FOLLOWING = "following"
        }
    }
}