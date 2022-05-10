package com.jomibusa.pruebaceiba.interfaces

import com.jomibusa.pruebaceiba.model.Post
import com.jomibusa.pruebaceiba.model.User
import com.jomibusa.pruebaceiba.util.EndPointList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(EndPointList.GET_USERS)
    fun getUsers(): Call<User>

    @GET(EndPointList.GET_POST_USER)
    fun getPostsByUser(@Query("userId") userId: String): Call<Post>

}