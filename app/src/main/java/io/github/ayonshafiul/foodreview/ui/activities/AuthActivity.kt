package io.github.ayonshafiul.foodreview.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.databinding.ActivityAuthBinding
import io.github.ayonshafiul.foodreview.repository.Repository
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModel
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModelFactory

class AuthActivity : AppCompatActivity() {

    private lateinit var viewModel: AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAuthBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        val view : View = findViewById(R.id.authHostFragment)

        binding.bottomNavigation.setupWithNavController(view.findNavController())

        viewModel = ViewModelProvider(this, Instances.authFactory).get(AuthViewModel::class.java)

    }
}