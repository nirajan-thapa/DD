package com.ddlite.restaurants.managers

import com.ddlite.restaurants.api.RestaurantApi
import com.ddlite.restaurants.api.Store
import com.ddlite.restaurants.models.Restaurant
import io.reactivex.rxjava3.core.Maybe

class RestaurantManager(
    private val restaurantApi: RestaurantApi
) : RestaurantRepository {

    override fun getRestaurantList(
        latitude: String,
        longitude: String,
        offset: Int,
        limit: Int
    ): Maybe<List<Restaurant>> {

        return restaurantApi.getRestaurantList(latitude, longitude, offset, limit)
            .map {
                it.map { it.toRestaurant() }
            }
    }

    private fun Store.toRestaurant() =
        Restaurant(
            id = id,
            name = name,
            description = description,
            coverImgUrl = cover_img_url,
            status = status,
            deliveryFee = delivery_fee
        )
}
