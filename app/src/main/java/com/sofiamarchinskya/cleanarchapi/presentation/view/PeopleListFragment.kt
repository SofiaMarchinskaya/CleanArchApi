package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.PopupMenu
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.data.Person
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPeopleListBinding
import com.sofiamarchinskya.cleanarchapi.presentation.view.adapter.PeopleListAdapter
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PeopleListViewModelFactory
import com.sofiamarchinskya.cleanarchapi.utils.Constants
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
        viewModel =
            ViewModelProvider(this, viewModelFactory)[PeopleListViewModel::class.java]
        peopleAdapter = PeopleListAdapter()
        binding =  FragmentPeopleListBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        setupSnackbar()
        setupNavigation()
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.people_frag_menu, menu)
    }

    private fun setupSnackbar() {
        //view?.setupSnackbar(this, viewModel.snackbarText, Snackbar.LENGTH_SHORT)
        //arguments?.let {
        //    viewModel.showEditResultMessage(args.userMessage)
        //}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean=
        when (item.itemId) {
            R.id.menu_clear -> {
                //viewModel.clearCompletedTasks()
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
//                viewModel.setFiltering(
//                    when (it.itemId) {
//                        R.id.active -> FilterType.ALL_PEOPLE
//                        R.id.completed -> FilterType.FAVORITES
//                        else -> TasksFilterType.ALL_TASKS
//                    }
//                )
                true
            }
            show()
        }
    }
    private fun setupNavigation() {
//        viewModel.openTaskEvent.observe(viewLifecycleOwner, EventObserver {
//            openTaskDetails(it)
//        })
//
    }
    private fun openAboutPersonFragment(data:Person) {
        view?.findNavController()?.navigate(
            R.id.action_peopleListFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_DATA to data)
        )
    }
}
