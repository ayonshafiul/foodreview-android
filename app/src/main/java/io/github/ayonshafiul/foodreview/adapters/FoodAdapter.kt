package io.github.ayonshafiul.foodreview.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.model.Food

class FoodAdapter(var list: List<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    fun submitList(list: List<Food>) {
        this.list = list
    }
    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.food_row_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val current = list[position]
        val foodName : TextView = holder.itemView.findViewById(R.id.foodName)
        val foodDescription : TextView = holder.itemView.findViewById(R.id.foodDescription)
        val foodPrice : TextView = holder.itemView.findViewById(R.id.foodPrice)
        val foodImage: ImageView = holder.itemView.findViewById(R.id.foodImage)
        foodName.text = current.foodName
        foodDescription.text = if (current.foodDescription.length > 15) current.foodDescription.substring(0, 15) + "..." else  current.foodDescription
        foodPrice.text = "Tk. " + current.foodPrice.toString()
        Glide.with(holder.itemView).load(R.drawable.food).into(foodImage)
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}