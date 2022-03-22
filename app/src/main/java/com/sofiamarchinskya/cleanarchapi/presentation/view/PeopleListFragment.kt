package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPeopleListBinding
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.PeopleListAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import javax.inject.Inject

class PeopleListFragment : Fragment() {
    private lateinit var binding: FragmentPeopleListBinding
    private lateinit var peopleAdapter: PeopleListAdapter
    private lateinit var viewModel: PeopleListViewModel

    @Inject
    lateinit var viewModelFactory: PeopleListViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[PeopleListViewModel::class.java]
        peopleAdapter = PeopleListAdapter(viewModel::openPersonDetails, viewModel::addFavorites)
        binding = FragmentPeopleListBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        viewModel.items.observe(viewLifecycleOwner) {
            peopleAdapter.update(it)
        }
        binding.personList.adapter = peopleAdapter
        viewModel.currentFilteringLabel.observe(viewLifecycleOwner) {
            binding.filteringText.text = getString(it)
        }
        viewModel.snackbarText.observe(viewLifecycleOwner) {
            showSnackbar(getString(it))
        }
        setupNavigation()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.people_frag_menu, menu)
    }

    private fun showSnackbar(text: String) {
        view?.let {
            Snackbar.make(
                it, text,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.menu_clear -> {
                viewModel.clearFavorites()
                true
            }
            R.id.menu_filter -> {
                showFilteringPopUpMenu()
                true
            }
            else -> false
        }

    private fun showFilteringPopUpMenu() {
        val view = activity?.findViewById<View>(R.id.menu_filter) ?: return
        PopupMenu(requireContext(), view).run {
            menuInflater.inflate(R.menu.filter_menu, menu)

            setOnMenuItemClickListener {
                viewModel.setFiltering(
                    when (it.itemId) {
                        R.id.active -> FilterType.FAVORITES
                        R.id.completed -> FilterType.NOT_FAVORITE
                        else -> FilterType.ALL_PEOPLE
                    }
                )
                true
            }
            show()
        }
    }

    private fun setupNavigation() {
        viewModel.openPersonDetailsEvent.observe(viewLifecycleOwner) {
            openAboutPersonFragment(it)
        }

    }

    private fun openAboutPersonFragment(url: String) {
        view?.findNavController()?.navigate(
            R.id.action_peopleListFragment_to_personDetailsFragment, bundleOf("url" to url)
        )
    }
}
