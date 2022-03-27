package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPeopleListBinding
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.PeopleListAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListEvent
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.utils.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PeopleListFragment : Fragment() {
    private lateinit var binding: FragmentPeopleListBinding
    private lateinit var peopleAdapter: PeopleListAdapter

    @Inject
    lateinit var viewModelFactory: PeopleListViewModelFactory

    private lateinit var viewModel: PeopleListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = FragmentPeopleListBinding.inflate(layoutInflater, container, false)
        viewModel=ViewModelProvider(this, viewModelFactory)[PeopleListViewModel::class.java]
        setHasOptionsMenu(true)
        peopleAdapter = PeopleListAdapter(viewModel::openPersonDetails, viewModel::addFavorites)
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.items.collect {
                peopleAdapter.update(it)
                Log.d("Бык","Сколлектился")
            }
        }
        binding.personList.adapter = peopleAdapter

        viewModel.eventsFlow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach {
            when (it) {
                is PeopleListEvent.ShowSnackBar -> {
                    showSnackbar(getString(it.res))
                }
                is PeopleListEvent.ChangeFilteringLabel -> {
                    binding.filteringText.text = getString(it.res)
                }
                else -> {
                    openAboutPersonFragment((it as PeopleListEvent.NavigateToPersonDetails).url)
                }
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
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

    private fun openAboutPersonFragment(url: String) {
        view?.findNavController()?.navigate(
            R.id.action_peopleListFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_URL to url)
        )
    }
}
