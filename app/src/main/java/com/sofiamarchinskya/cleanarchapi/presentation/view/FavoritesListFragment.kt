package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sofiamarchinskya.cleanarchapi.Constants
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentFavouriteListBinding
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.PeopleListAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.FavoritesListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.FavoritesListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import javax.inject.Inject


class FavoritesListFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteListBinding
    private lateinit var listAdapter: PeopleListAdapter
    private lateinit var viewModel: FavoritesListViewModel
    @Inject
    lateinit var viewModelFactory: FavoritesListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this,viewModelFactory)[FavoritesListViewModel::class.java]
        binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        listAdapter = PeopleListAdapter(
            ::openAboutPersonFragment,
        )
        binding.favoriteList.layoutManager = LinearLayoutManager(requireContext())
        viewModel.favoriteListMutable.observe(viewLifecycleOwner){
            listAdapter.update(it)
            Log.d("Бык","скачади список из бд ${it.size}")
        }
        binding.favoriteList.adapter = listAdapter
        return binding.root
    }

    private fun openAboutPersonFragment(data: DomainPersonModel) {
        view?.findNavController()?.navigate(
            R.id.action_favouriteFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_DATA to data)
        )
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            FavoritesListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}