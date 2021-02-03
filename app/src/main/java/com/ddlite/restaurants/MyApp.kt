package com.ddlite.restaurants

import android.app.Application
import com.ddlite.restaurants.api.networkModule
import com.ddlite.restaurants.managers.managerModule
import com.ddlite.restaurants.ui.list.restaurantListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                restaurantListModule,
                managerModule,
                networkModule
            )
        }
    }
}
