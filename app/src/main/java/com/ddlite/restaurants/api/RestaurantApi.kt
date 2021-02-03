package com.ddlite.restaurants.api

import io.reactivex.rxjava3.core.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantApi {

    @GET("/v2/restaurant")
    fun getRestaurantList(
        @Query("lat") latitude: String,
        @Query("lng") longitude: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Maybe<List<Store>>
}
