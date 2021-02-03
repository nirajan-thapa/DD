package com.ddlite.restaurants.ui.list

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ddlite.R
import com.ddlite.restaurants.models.Restaurant
import kotlinx.android.synthetic.main.activity_restaurant_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class RestaurantListActivity : AppCompatActivity() {

    private val viewModel: RestaurantListViewModel by viewModel()

    private val adapter: RestaurantListAdapter by lazy {
        RestaurantListAdapter { restaurant -> onRestaurantClick(restaurant) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_restaurant_list)
        setSupportActionBar(toolbar)

        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.state.observe(
            this,
            Observer {
                when (it) {
                    State.Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is State.Loaded -> {
                        progressBar.visibility = View.GONE
                        adapter.submitList(it.restaurants)
                    }
                    State.Error -> {
                        progressBar.visibility = View.GONE
                    }
                }
            }
        )
    }

    private fun onRestaurantClick(restaurant: Restaurant) {}
}
