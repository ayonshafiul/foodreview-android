package io.github.ayonshafiul.foodreview.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.adapters.FoodAdapter
import io.github.ayonshafiul.foodreview.adapters.RestaurantAdapter
import io.github.ayonshafiul.foodreview.databinding.FragmentHomeBinding
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var token: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(requireActivity(), Instances.homeFactory).get(HomeViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        sharedPrefs = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!
        token = sharedPrefs.getString(getString(R.string.token_key), "")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTopRestaurants(token)
        viewModel.topRestaurants.observe(requireActivity()) {
            val topRestaurantsAdapter = RestaurantAdapter(it)
            val horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

            binding.topRestaurantsrv.apply {
                adapter = topRestaurantsAdapter
                layoutManager = horizontalLayoutManager
            }
        }

        viewModel.getPopularRestaurants(token)
        viewModel.popularRestaurants.observe(requireActivity()) {
            val popularRestaurantsAdapter = RestaurantAdapter(it)
            val horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

            binding.popularRestaurantsrv.apply {
                adapter = popularRestaurantsAdapter
                layoutManager = horizontalLayoutManager
            }
        }

        viewModel.getTopFoodItems(token)
        viewModel.topFoodItems.observe(requireActivity()) {
            val topFoodItemsAdapter = FoodAdapter(it)
            val horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

            binding.topFoodItemsrv.apply {
                adapter = topFoodItemsAdapter
                layoutManager = horizontalLayoutManager
            }
        }


        viewModel.getPopularFoodItems(token)
        viewModel.popularFoodItems.observe(requireActivity()) {
            val topRestaurantsAdapter = FoodAdapter(it)
            val horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

            binding.popularFoodItemsrv.apply {
                adapter = topRestaurantsAdapter
                layoutManager = horizontalLayoutManager
            }
        }
    }


}
