package com.ddlite.restaurants.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ddlite.restaurants.managers.RestaurantRepository
import com.ddlite.restaurants.models.Restaurant
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkClass
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.get
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock

class RestaurantListViewModelTest : KoinTest {

    private lateinit var restaurantRepository: RestaurantRepository
    private lateinit var restaurantListViewModel: RestaurantListViewModel
    private lateinit var observer: Observer<State>
    private val _state = MutableLiveData<State>()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val koinTestRule = KoinTestRule.create {}

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        // Your way to build a Mock here
        mockkClass(clazz)
    }

    @Before
    fun before() {
        restaurantRepository = declareMock<RestaurantRepository>()
        restaurantListViewModel = declareMock<RestaurantListViewModel>()
        observer = mockk<Observer<State>>(relaxed = true)

        every { restaurantListViewModel.state } returns _state
    }

    @Test
    fun `should inject components`() {
        Assert.assertNotNull(get<RestaurantListViewModel>())
        Assert.assertNotNull(get<RestaurantRepository>())
    }

    @Test
    fun `verify loading state enabled when requesting restaurants`() {
        every {
            restaurantListViewModel.getRestaurantList(any(), any(), any(), any())
        } returns _state.postValue(State.Loading)

        restaurantListViewModel.getRestaurantList(
            latitude = "37.422740",
            longitude = "-122.139956",
            limit = 50,
            offset = 10
        )
        restaurantListViewModel.state.observeForever(observer)
        verify { observer.onChanged(State.Loading) }
    }

    @Test
    fun `verify restaurants list updated when request restaurants is success`() {
        val loadedState = State.Loaded(mockRestaurantsList())

        every {
            restaurantListViewModel.getRestaurantList(any(), any(), any(), any())
        } returns _state.postValue(loadedState)

        restaurantListViewModel.getRestaurantList(
            latitude = "37.422740",
            longitude = "-122.139956",
            limit = 50,
            offset = 10
        )
        restaurantListViewModel.state.observeForever(observer)
        verify { observer.onChanged(loadedState) }
    }

    private fun mockRestaurantsList() =
        listOf(
            Restaurant(
                1,
                "Wendy's",
                "Fast Food",
                "",
                "Open",
                "$1.00"
            )
        )
}
