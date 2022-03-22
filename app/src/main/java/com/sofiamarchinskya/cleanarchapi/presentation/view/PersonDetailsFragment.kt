package com.sofiamarchinskya.cleanarchapi.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sofiamarchinskya.cleanarchapi.app.App
import com.sofiamarchinskya.cleanarchapi.databinding.FragmentPersonDetailsBinding
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModel
import com.sofiamarchinskya.cleanarchapi.presentation.viewmodel.PersonDetailsViewModelFactory
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
        val args = arguments?.getString("url")

        if (args != null) {
            viewModel.start(args)
        }
        viewModel.person.observe(viewLifecycleOwner) {
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
        viewModel._snackbarText.observe(viewLifecycleOwner) {
            showSnackBar(getString(it))
        }
        binding.checkBox.setOnClickListener {
            viewModel.addFavorites((it as CheckBox).isChecked)
        }
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