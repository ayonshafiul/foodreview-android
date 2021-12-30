package io.github.ayonshafiul.foodreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.databinding.FragmentLoginBinding
import io.github.ayonshafiul.foodreview.databinding.FragmentRegisterBinding
import io.github.ayonshafiul.foodreview.model.User
import io.github.ayonshafiul.foodreview.utils.Instances
import io.github.ayonshafiul.foodreview.viewmodel.AuthViewModel


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    lateinit var viewModel: AuthViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container, false);
        viewModel = ViewModelProvider(requireActivity(), Instances.authFactory).get(AuthViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val email = binding.registerEmail.editText?.text.toString()
            val password = binding.registerPassword.editText?.text.toString()
            val confirmPassword = binding.registerConfirmPassword.editText?.text.toString()
            if(password != confirmPassword) {
                Toast.makeText(activity?.applicationContext, "Password and confirm password doesn't match", Toast.LENGTH_SHORT).show()
            } else {
               viewModel.register(User(email, password))
            }

        }
        viewModel.msgResponse.observe(requireActivity()) {
            if(it.success) {
                Navigation.findNavController(binding.root).navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(activity?.applicationContext, it.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }
}