package com.ddlite.restaurants.managers

import com.ddlite.restaurants.models.Restaurant
import io.reactivex.rxjava3.core.Maybe

interface RestaurantRepository {

    fun getRestaurantList(
        latitude: String,
        longitude: String,
        offset: Int,
        limit: Int
    ) : Maybe<List<Restaurant>>
}
