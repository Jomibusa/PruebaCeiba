package com.jomibusa.pruebaceiba.interfaces

import com.jomibusa.pruebaceiba.model.Post
import com.jomibusa.pruebaceiba.model.User
import com.jomibusa.pruebaceiba.util.EndPointList
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET(EndPointList.GET_USERS)
    fun getUsers(): Call<List<User>>

    @GET(EndPointList.GET_POST_USER)
    fun getPostsByUser(@Query("userId") userId: String): Call<List<Post>>

    companion object {

        var BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }

}