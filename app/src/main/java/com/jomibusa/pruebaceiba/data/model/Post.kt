package com.jomibusa.pruebaceiba.data.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("userId") val userID: Int,
    @SerializedName("id") val postID: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
)
