package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
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
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.PeopleListAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.FavoritesListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.FavoritesListViewModelFactory
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
        binding = FragmentFavouriteListBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[FavoritesListViewModel::class.java]
        listAdapter = PeopleListAdapter(
            viewModel::onAboutItemClicked,
        )
        viewModel.apply {
            allFavorites.observe(viewLifecycleOwner) {
                listAdapter.update(it)
            }
            onNoteItemClickEvent.observe(viewLifecycleOwner) {
                openAboutPersonFragment(it)
            }
        }
        binding.apply {
            favoriteList.layoutManager = LinearLayoutManager(requireContext())
            favoriteList.adapter = listAdapter
        }
        return binding.root
    }

    private fun openAboutPersonFragment(data: UIModel) {
        view?.findNavController()?.navigate(
            R.id.action_favouriteFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_DATA to data)
        )
    }
}