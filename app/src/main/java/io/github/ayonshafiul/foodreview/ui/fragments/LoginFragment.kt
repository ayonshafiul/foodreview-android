package io.github.ayonshafiul.foodreview.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.databinding.FragmentLoginBinding
import io.github.ayonshafiul.foodreview.databinding.FragmentRegisterBinding
import io.github.ayonshafiul.foodreview.model.User
import io.github.ayonshafiul.foodreview.ui.activities.MainActivity
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModel


class LoginFragment : Fragment() {

    lateinit var binding: FragmentLoginBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), Instances.authFactory).get(AuthViewModel::class.java)

        binding.loginButton.setOnClickListener {
            val user = User(binding.loginEmail.editText?.text.toString(), binding.loginPassword.editText?.text.toString())
            val token = viewModel.login(user)
            viewModel.tokenResponse.observe(requireActivity()) {
                val token = it.token
                if(it.success) {
                    val sharedPref = activity?.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE)
                    with (sharedPref?.edit()) {
                        this?.putString(getString(R.string.token_key), token)
                        this?.apply()
                    }
                    activity?.startActivity(Intent(activity?.applicationContext, MainActivity::class.java))
                    activity?.finish()
                } else{
                    Toast.makeText(activity?.applicationContext, "${it.token}", Toast.LENGTH_SHORT).show()
                }

                Log.d("Response", "onViewCreated: ${it.token}")

            }

        }

    }
}