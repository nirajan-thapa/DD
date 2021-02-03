package com.ddlite.restaurants.api

import com.google.gson.annotations.SerializedName

data class Store(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("cover_img_url")
    val cover_img_url: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("delivery_fee")
    val delivery_fee: String
)
