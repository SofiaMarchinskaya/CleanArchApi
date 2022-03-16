package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentMainListBinding

class MainListFragment : Fragment() {
    private lateinit var binding: FragmentMainListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainListBinding.inflate(inflater, container, false)
        return binding.root
    }
}