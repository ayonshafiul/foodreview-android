package io.github.ayonshafiul.foodreview.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import io.github.ayonshafiul.foodreview.adapters.RestaurantAdapter
import io.github.ayonshafiul.foodreview.adapters.ReviewAdapter
import io.github.ayonshafiul.foodreview.databinding.FragmentFoodDetailsBinding
import io.github.ayonshafiul.foodreview.model.ReviewBody
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.FoodDetailsViewModel

class FoodDetailsFragment : Fragment() {
    private lateinit var binding: FragmentFoodDetailsBinding
    val args: FoodDetailsFragmentArgs by navArgs()
    private lateinit var viewModel : FoodDetailsViewModel
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var token : String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_food_details, container, false)
        viewModel = ViewModelProvider(requireActivity(), Instances.foodDetailsFactory).get(FoodDetailsViewModel::class.java)
        sharedPrefs = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!
        token = sharedPrefs.getString(getString(R.string.token_key), "")!!
        binding.viewModel = viewModel
        binding.lifecycleOwner = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFoodDetails(token, args.foodID)
        viewModel.getFoodReviews(token, args.foodID)

        binding.foodReviewsrv.layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)

        binding.foodReviewButton.setOnClickListener{
            val reviewText = binding.foodTextInputLayout.editText?.text.toString()
            val reviewRating = binding.foodRatingBar.rating.toDouble() * 2
            if(reviewText == "") {
                Toast.makeText(requireContext(), "Please type a review", Toast.LENGTH_SHORT).show()
            } else {
                val reviewBody = ReviewBody(reviewRating, reviewText)
                viewModel.postFoodReview(token, reviewBody, args.foodID)
                Toast.makeText(requireContext(), "Inserted Review Successfully", Toast.LENGTH_SHORT).show()
                refresh()
                binding.foodTextInputLayout.editText?.text?.clear()
            }

        }

        viewModel.reviews.observe(requireActivity()) {

            binding.foodReviewsrv.adapter = ReviewAdapter(it)
            binding.foodReviewsrv.adapter?.notifyDataSetChanged()
        }
    }

    fun refresh() {
        viewModel.getFoodDetails(token, args.foodID)
        viewModel.getFoodReviews(token, args.foodID)
    }
}