package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onPause() {
        super.onPause()
            val currentFragment = requireActivity().findNavController(R.id.nav_host_fragment).currentDestination?.id
            if(currentFragment!= R.id.peopleListFragment)
                requireActivity().findNavController(R.id.nav_host_fragment)
                    .navigate(R.id.action_personDetailsFragment_to_peopleListFragment)
    }
}