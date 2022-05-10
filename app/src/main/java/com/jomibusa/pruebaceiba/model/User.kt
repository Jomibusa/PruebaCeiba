package com.jomibusa.pruebaceiba.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val userID: Int,
    @SerializedName("name") val name: String,
    @SerializedName("username") val userName: String,
    @SerializedName("email") val email: String,
    @SerializedName("address") val address: Address,
    @SerializedName("phone") val phone: String,
    @SerializedName("website") val webSite: String,
    @SerializedName("company") val company: Company,
)

data class Address(
    @SerializedName("street") val street: String,
    @SerializedName("suite") val suite: String,
    @SerializedName("city") val city: String,
    @SerializedName("zipcode") val zipCode: String,
    @SerializedName("geo") val geo: Geo
)

data class Geo(
    @SerializedName("lat") val lat: String,
    @SerializedName("lng") val lng: String
)

data class Company(
    @SerializedName("name") val lat: String,
    @SerializedName("catchPhrase") val catchPhrase: String,
    @SerializedName("bs") val bs: String,
)
