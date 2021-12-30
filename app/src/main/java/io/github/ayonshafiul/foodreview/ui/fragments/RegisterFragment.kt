package io.github.ayonshafiul.foodreview.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.ayonshafiul.foodreview.R
import io.github.ayonshafiul.foodreview.databinding.FragmentLoginBinding
import io.github.ayonshafiul.foodreview.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container, false);

        return binding.root
    }
}