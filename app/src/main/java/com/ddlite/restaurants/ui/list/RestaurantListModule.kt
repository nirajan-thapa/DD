package com.ddlite.restaurants.ui.list

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val restaurantListModule = module {

    viewModel {
        RestaurantListViewModel(
            get()
        )
    }
}
