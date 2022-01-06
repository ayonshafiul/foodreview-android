package io.github.ayonshafiul.foodreview.adapters



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.model.Food
import io.github.ayonshafiul.foodreview.model.Review
import io.github.ayonshafiul.foodreview.ui.fragments.HomeFragmentDirections

class ReviewAdapter(var list: List<Review>) : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    fun submitList(list: List<Review>) {
        this.list = list
    }
    inner class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.review_row_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val current = list[position]
        val reviewTv : TextView = holder.itemView.findViewById(R.id.reviewTv)
        val ratingTv : TextView = holder.itemView.findViewById(R.id.ratingTv)

        reviewTv.text = "\"" + current.review + "\""
        ratingTv.text = current.rating.toString() + "/10"
    }

    override fun getItemCount(): Int {
        return list.count()
    }
}