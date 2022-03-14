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
    override fun onPause() {
        super.onPause()
        val currentFragment = requireActivity().findNavController(R.id.nav_host_fragment_favorites).currentDestination?.id
        if(currentFragment!= R.id.favouriteFragment)
            requireActivity().findNavController(R.id.nav_host_fragment_favorites)
                .navigate(R.id.action_personDetailsFragment_to_favouriteFragment)
    }
}