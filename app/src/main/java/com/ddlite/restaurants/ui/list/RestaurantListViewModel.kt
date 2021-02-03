package com.ddlite.restaurants.ui.list

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ddlite.restaurants.BaseViewModel
import com.ddlite.restaurants.managers.RestaurantRepository
import com.ddlite.restaurants.models.Restaurant
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

sealed class State {
    object Loading : State()

    data class Loaded(
        val restaurants: List<Restaurant>
    ) : State()

    object Error : State()
}

class RestaurantListViewModel(
    private val restaurantRepository: RestaurantRepository
) : BaseViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        getRestaurantList(
            latitude = "37.422740",
            longitude = "-122.139956",
            limit = 50,
            offset = 10
        )
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getRestaurantList(
        latitude: String,
        longitude: String,
        offset: Int,
        limit: Int
    ) {
        setLoading()
        disposable.add(
            restaurantRepository.getRestaurantList(
                latitude = latitude,
                longitude = longitude,
                limit = limit,
                offset = offset
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { setData(it) },
                    { setError() }
                )
        )
    }

    private fun setLoading() {
        _state.value = State.Loading
    }

    private fun setData(restaurants: List<Restaurant>) {
        _state.value = State.Loaded(restaurants)
    }

    private fun setError() {
        _state.value = State.Error
    }
}
