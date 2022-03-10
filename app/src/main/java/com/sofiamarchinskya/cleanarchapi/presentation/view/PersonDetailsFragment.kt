package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.sofiamarchinskya.cleanarchapi.presentation.model.UIModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class PersonDetailsFragment : Fragment() {
    private var personData: UIModel? = null
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

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[PersonDetailsViewModel::class.java]
        binding = FragmentPersonDetailsBinding.inflate(inflater, container, false)
        arguments?.let {
            personData = it.getParcelable(Constants.PERSON_DATA)
        }
        binding.apply {
            name.text = personData?.name
            height.text = "height: ${personData?.height}"
            mass.text = "weight: ${personData?.mass}"
            hairColor.text = "hair color: ${personData?.hair_color}"
            skinColor.text = "skin color: ${personData?.skin_color}"
            eyesColor.text = "eye color: ${personData?.eye_color}"
            birthYear.text = "birth year: ${personData?.birth_year}"
            gender.text = "gender: ${personData?.gender}"
            checkBox.isChecked = personData?.isFavourite == true
        }

        binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                lifecycleScope.launch {
                    personData?.let {
                        viewModel.addToFavourites(it)
                    }
                }
            } else {
                lifecycleScope.launch {
                    personData?.let {
                        viewModel.deleteFromFav(it.url)
                    }
                }
            }
        }
        return binding.root
    }
}