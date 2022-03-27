package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPersonDetailsBinding
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsEvent
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import com.sofiamarchinskya.cleanarchapi.utils.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PersonDetailsFragment : Fragment() {
    private lateinit var binding: FragmentPersonDetailsBinding
    private lateinit var viewModel: PersonDetailsViewModel

    @Inject
    lateinit var viewModelFactory: PersonDetailsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonDetailsViewModel::class.java]
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        arguments?.getString(Constants.PERSON_URL)?.let {
            viewModel.loadPerson(it)
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.person.collect {
                binding.apply {
                    birthYear.text = it?.birth_year
                    skinColor.text = it?.skin_color
                    eyesColor.text = it?.eye_color
                    name.text = it?.name
                    gender.text = it?.gender
                    hairColor.text = it?.hair_color
                    height.text = it?.height.toString()
                    mass.text = it?.mass.toString()
                    checkBox.isChecked = it?.isfavorite == true
                }
            }
        }

        binding.checkBox.setOnCheckedChangeListener { _, flag ->
            viewModel.addFavorites(flag)
        }

        viewModel.eventsFlow.flowWithLifecycle(
            viewLifecycleOwner.lifecycle,
            Lifecycle.State.STARTED
        ).onEach {
            when (it) {
                is PersonDetailsEvent.ShowSnackBar -> showSnackBar(getString(it.res))
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        return binding.root
    }

    private fun showSnackBar(msg: String) {
        view?.let {
            Snackbar.make(
                it, msg,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}