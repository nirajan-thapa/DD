package com.ddlite.restaurants.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ddlite.R
import com.ddlite.restaurants.models.Restaurant
import com.squareup.picasso.Picasso

class RestaurantListAdapter(
    private val onClick: (Restaurant) -> Unit
) : ListAdapter<Restaurant, RestaurantListAdapter.ViewHolder>(
    RestaurantDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant, onClick)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val restaurantImage: AppCompatImageView = view.findViewById(R.id.restaurant_image)
        private val restaurantName: AppCompatTextView = view.findViewById(R.id.restaurant_name)
        private val restaurantDesc: AppCompatTextView = view.findViewById(R.id.restaurant_desc)
        private val restaurantStatus: AppCompatTextView = view.findViewById(R.id.restaurant_status)

        fun bind(restaurant: Restaurant, onClick: (Restaurant) -> Unit) {
            Picasso.get()
                .load(restaurant.coverImgUrl)
                .into(restaurantImage)
            restaurantName.text = restaurant.name
            restaurantDesc.text = restaurant.description
            restaurantStatus.text = restaurant.status
            itemView.setOnClickListener { onClick(restaurant) }
        }
    }

    object RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {

        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
