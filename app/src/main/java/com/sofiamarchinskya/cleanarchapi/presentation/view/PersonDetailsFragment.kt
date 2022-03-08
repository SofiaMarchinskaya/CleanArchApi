package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sofiamarchinskya.cleanarchapi.Constants
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPersonDetailsBinding
import com.sofiamarchinskya.cleanarchapi.domain.model.DomainPersonModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonDetailsFragment : Fragment() {
    private var personData: DomainPersonModel? = null
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
        viewModel = ViewModelProvider(this,viewModelFactory)[PersonDetailsViewModel::class.java]
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            personData = it.getParcelable(Constants.PERSON_DATA)
        }
        binding.apply {
            name.text = personData?.name
            age.text = personData?.height.toString()
            color.text = personData?.url
        }

        viewModel.isChecked.observe(viewLifecycleOwner) {
            binding.checkBox.isChecked = it
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                lifecycleScope.launch {
                    personData?.let {
                        viewModel.addToFavourites(it)
                        Log.d("Бык","отправили данные ${it.name}")
                    }

                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(personData: DomainPersonModel) =
            PersonDetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(Constants.PERSON_DATA, personData)
                }
            }
    }
}