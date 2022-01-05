package io.github.ayonshafiul.foodreview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.model.Restaurant

class RestaurantAdapter(var list: List<Restaurant>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    fun submitList(list: List<Restaurant>) {
        this.list = list
    }
    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_row_item, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val current = list[position]
        val restaurantName : TextView= holder.itemView.findViewById(R.id.restaurantName)
        val restaurantDescription : TextView= holder.itemView.findViewById(R.id.restaurantDescription)
        val restaurantRating : TextView= holder.itemView.findViewById(R.id.restaurantRating)
        val restaurantImage: ImageView = holder.itemView.findViewById(R.id.restaurantImage)
        restaurantName.text = current.restaurantName
        restaurantDescription.text = if (current.restaurantDescription.length > 15) current.restaurantDescription.substring(0, 15) + "..." else  current.restaurantDescription
        restaurantRating.text = current.rating.toString() + " / 10"
        Glide.with(holder.itemView).load(R.drawable.restaurant).into(restaurantImage)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}