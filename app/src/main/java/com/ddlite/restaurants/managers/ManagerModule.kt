package com.ddlite.restaurants.managers

import org.koin.dsl.binds
import org.koin.dsl.module

val managerModule = module {

    single {
        RestaurantManager(
            get()
        )
    } binds arrayOf(
        RestaurantRepository::class
    )
}
