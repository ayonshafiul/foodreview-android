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
import androidx.navigation.findNavController
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
    private  var topRestaurantAdapter: RestaurantAdapter? = null
    private  var popularRestaurantAdapter: RestaurantAdapter? = null
    private  var topFoodItemsAdapter: FoodAdapter? = null
    private  var popularFoodItemsAdapter: FoodAdapter? = null

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
        var horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)

        viewModel.getTopRestaurants(token)
        viewModel.getPopularRestaurants(token)
        viewModel.getTopFoodItems(token)
        viewModel.getPopularFoodItems(token)


        binding.topRestaurantsrv.layoutManager = horizontalLayoutManager
        viewModel.topRestaurants.observe(requireActivity()) {
            topRestaurantAdapter = RestaurantAdapter(it)
            binding.topRestaurantsrv.adapter = topRestaurantAdapter
            binding.topRestaurantsrv.adapter?.notifyDataSetChanged()
        }

        horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.popularRestaurantsrv.layoutManager = horizontalLayoutManager

        viewModel.popularRestaurants.observe(requireActivity()) {
            popularRestaurantAdapter = RestaurantAdapter(it)
            binding.popularRestaurantsrv.adapter = popularRestaurantAdapter
            binding.popularRestaurantsrv.adapter?.notifyDataSetChanged()
        }

        horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.topFoodItemsrv.layoutManager = horizontalLayoutManager

        viewModel.topFoodItems.observe(requireActivity()) {
            topFoodItemsAdapter = FoodAdapter(it)
            binding.topFoodItemsrv.adapter = topFoodItemsAdapter
            binding.topFoodItemsrv.adapter?.notifyDataSetChanged()
        }


        horizontalLayoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.popularFoodItemsrv.layoutManager = horizontalLayoutManager

        viewModel.popularFoodItems.observe(requireActivity()) {
            popularFoodItemsAdapter = FoodAdapter(it)
            binding.popularFoodItemsrv.adapter = popularFoodItemsAdapter
            binding.popularFoodItemsrv.adapter?.notifyDataSetChanged()
        }
    }


}
