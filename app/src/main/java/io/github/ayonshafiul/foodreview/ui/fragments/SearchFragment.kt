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
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.adapters.FoodAdapter
import io.github.ayonshafiul.foodreview.adapters.RestaurantAdapter
import io.github.ayonshafiul.foodreview.databinding.FragmentHomeBinding
import io.github.ayonshafiul.foodreview.databinding.FragmentSearchBinding
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.HomeViewModel
import io.github.ayonshafiul.foodreview.viewmodel.SearchViewModel


class SearchFragment : Fragment() {
    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: FragmentSearchBinding
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var token: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(requireActivity(), Instances.searchFactory).get(SearchViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        sharedPrefs = activity?.getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)!!
        token = sharedPrefs.getString(getString(R.string.token_key), "")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchButton.setOnClickListener{
            var query = binding.searchInput.editText?.text.toString()
            if(query.length < 3) {
                Toast.makeText(requireContext(), "Please type at least three characters to search", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.searchFoodItems(token, query)
                viewModel.searchRestaurants(token, query)
            }
            binding.foodSearchrv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
            viewModel.foodItems.observe(requireActivity()) {
                binding.foodSearchrv.adapter = FoodAdapter(it, "search")
                binding.foodSearchrv.adapter?.notifyDataSetChanged()
            }
            binding.restaurantSearchrv.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false);
            viewModel.restaurants.observe(requireActivity()) {
                binding.restaurantSearchrv.adapter = RestaurantAdapter(it, "search")
                binding.restaurantSearchrv.adapter?.notifyDataSetChanged()
            }
        }
    }
}