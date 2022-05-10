package com.jomibusa.pruebaceiba.data

import android.util.Log
import com.jomibusa.pruebaceiba.interfaces.ApiService
import com.jomibusa.pruebaceiba.model.Post
import com.jomibusa.pruebaceiba.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitManager {

    private val TAG = "RetrofitManager"

    fun getListUsers(listener: (List<User>?) -> Unit) {
        val apiService = ApiService.create().getUsers()
        apiService.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>?, response: Response<List<User>>?) {
                if (response?.body() != null) {
                    listener(response.body())
                    Log.d(TAG, "onResponse ${response.body()}")
                } else {
                    listener(null)
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                listener(null)
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }

    fun getListPosts(userID: String, listener: (List<Post>?) -> Unit) {
        val apiService = ApiService.create().getPostsByUser(userID)
        apiService.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                if (response?.body() != null) {
                    listener(response.body())
                    Log.d(TAG, "onResponse ${response.body()}")
                } else {
                    listener(null)
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                listener(null)
                Log.d(TAG, "onFailure ${t.message}")
            }
        })
    }

}