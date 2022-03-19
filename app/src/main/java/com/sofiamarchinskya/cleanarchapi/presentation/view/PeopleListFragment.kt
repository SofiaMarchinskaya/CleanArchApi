package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sofiamarchinskya.cleanarchapi.utils.Constants
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPeopleListBinding
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val broadCastReceiver = object : BroadcastReceiver() {
            override fun onReceive(contxt: Context?, intent: Intent?) {
                when (intent?.action) {
                    Constants.ITEM_CHANGED -> viewModel.getList()
                }
            }
        }
        LocalBroadcastManager.getInstance(requireContext())
            .registerReceiver(broadCastReceiver, IntentFilter(Constants.ITEM_CHANGED))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel =
            ViewModelProvider(this, viewModelFactory)[PeopleListViewModel::class.java]
        peopleAdapter = PeopleListAdapter(viewModel::onAboutItemClicked)
        binding = FragmentPeopleListBinding.inflate(layoutInflater, container, false).apply {
            peopleList.layoutManager = LinearLayoutManager(requireContext())
            peopleList.adapter = peopleAdapter
        }
        viewModel.apply {
            getList()
            personList.observe(viewLifecycleOwner) {
                peopleAdapter.update(it)
            }
        }
        viewModel.onNoteItemClickEvent.observe(viewLifecycleOwner) {
            openAboutPersonFragment(it)
        }
        return binding.root
    }

    private fun openAboutPersonFragment(data: UIModel) {
        view?.findNavController()?.navigate(
            R.id.action_peopleListFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_DATA to data)
        )
    }
}
