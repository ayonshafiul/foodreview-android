package io.github.ayonshafiul.foodreview.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.adapters.FoodAdapter
import io.github.ayonshafiul.foodreview.adapters.ReviewAdapter
import io.github.ayonshafiul.foodreview.databinding.FragmentFoodDetailsBinding
import io.github.ayonshafiul.foodreview.databinding.FragmentRestaurantDetailsBinding
import io.github.ayonshafiul.foodreview.model.ReviewBody
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.FoodDetailsViewModel
import io.github.ayonshafiul.foodreview.viewmodel.RestaurantDetailsViewModel


class RestaurantDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRestaurantDetailsBinding
    val args: RestaurantDetailsFragmentArgs by navArgs()
    private lateinit var viewModel : RestaurantDetailsViewModel
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var token : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_details, container, false)
        viewModel = ViewModelProvider(requireActivity(), Instances.restauratDetailsFactory).get(
            RestaurantDetailsViewModel::class.java)
        sharedPrefs = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!
        token = sharedPrefs.getString(getString(R.string.token_key), "")!!
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getRestaurantDetails(token, args.restaurantID)
        viewModel.getRestaurantReviews(token, args.restaurantID)
        viewModel.getRestaurantFoodItems(token, args.restaurantID)

        binding.restaurantReviewsrv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        binding.restaurantReviewButton.setOnClickListener{
            val reviewText = binding.restaurantTextInputLayout.editText?.text.toString()
            val reviewRating = binding.restaurantRatingBar.rating.toDouble() * 2
            if(reviewText == "") {
                Toast.makeText(requireContext(), "Please type a review", Toast.LENGTH_SHORT).show()
            } else {
                val reviewBody = ReviewBody(reviewRating.toDouble(), reviewText)
                viewModel.postRestaurantReview(token, reviewBody, args.restaurantID)
                Toast.makeText(requireContext(), "Inserted Review Successfully", Toast.LENGTH_SHORT).show()
                refresh()
                binding.restaurantTextInputLayout.editText?.text?.clear()
            }

        }
        binding.restaurantFoodItemsrv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.reviews.observe(requireActivity()) {
            binding.restaurantReviewsrv.adapter = ReviewAdapter(it)
            binding.restaurantReviewsrv.adapter?.notifyDataSetChanged()
        }
        viewModel.foodItems.observe(requireActivity()) {
            binding.restaurantFoodItemsrv.adapter = FoodAdapter(it, "restaurant")
            binding.restaurantFoodItemsrv.adapter?.notifyDataSetChanged()
        }

    }
    fun refresh() {
        viewModel.getRestaurantDetails(token, args.restaurantID)
        viewModel.getRestaurantReviews(token, args.restaurantID)
    }

}