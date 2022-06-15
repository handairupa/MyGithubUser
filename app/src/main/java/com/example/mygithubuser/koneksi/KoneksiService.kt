package com.example.mygithubuser.koneksi


import com.example.mygithubuser.detail.ResponseDetail
import com.example.mygithubuser.detail.ResponseFollowersItem
import com.example.mygithubuser.detail.ResponseFollowingItem
import com.example.mygithubuser.find.ResponsePencarian
import retrofit2.Call
import retrofit2.http.*

interface KoneksiService {

    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getpencarian( @Query("q") username: String): Call<ResponsePencarian>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getdetailUser(
            @Path("username")username: String):Call <ResponseDetail>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollower(
        @Path("username")username: String):Call <List<ResponseFollowersItem>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username")username: String):Call <List<ResponseFollowingItem>>
}