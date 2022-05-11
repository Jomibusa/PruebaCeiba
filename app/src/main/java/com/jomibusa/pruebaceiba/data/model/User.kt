package com.jomibusa.pruebaceiba.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("id") val userID: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String? = null,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: Address? = null,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val webSite: String? = null,
    @SerializedName("company") val company: Company? = null,
) : Serializable

data class Address(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipCode: String,
    @SerializedName("geo") val geo: Geo
) : Serializable

data class Geo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
) : Serializable

data class Company(
    @SerializedName("name") val lat: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val bs: String,
) : Serializable
