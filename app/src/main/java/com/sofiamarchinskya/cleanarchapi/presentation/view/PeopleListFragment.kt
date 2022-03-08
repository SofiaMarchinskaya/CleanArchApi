package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sofiamarchinskya.cleanarchapi.Constants
import com.sofiamarchinskya.cleanarchapi.R
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.core.domain.ListMapper
import com.sofiamarchinskya.cleanarchapi.core.domain.ListMapperImpl
import com.sofiamarchinskya.cleanarchapi.core.domain.Mapper
import com.sofiamarchinskya.cleanarchapi.core.domain.MapperImpl
import com.sofiamarchinskya.cleanarchapi.data.DataPerson
import com.sofiamarchinskya.cleanarchapi.data.PersonPreferencesImpl
import com.sofiamarchinskya.cleanarchapi.data.StarWarsRepositoryImpl
import com.sofiamarchinskya.cleanarchapi.data.net.PersonServerModel
import com.sofiamarchinskya.cleanarchapi.data.net.StarWarsServiceImpl
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPeopleListBinding
import com.sofiamarchinskya.cleanarchapi.domain.StarWarsInteractor
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
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
        binding = FragmentPeopleListBinding.inflate(layoutInflater, container, false)
        peopleAdapter = PeopleListAdapter(::openAboutPersonFragment)
        binding.peopleList.layoutManager = LinearLayoutManager(requireContext())
        binding.peopleList.adapter = peopleAdapter
        viewModel = ViewModelProvider(this,viewModelFactory)[PeopleListViewModel::class.java]
        viewModel.getList()
        viewModel.personList.observe(viewLifecycleOwner){
            peopleAdapter.update(it)
        }
        return binding.root
    }
    private fun openAboutPersonFragment(data: DomainPersonModel) {
        view?.findNavController()?.navigate(
            R.id.action_peopleListFragment_to_personDetailsFragment,
            bundleOf(Constants.PERSON_DATA to data)
        )
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PeopleListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}